package tn.esprit.wellbeing.modules.occurences.batch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import tn.esprit.wellbeing.modules.occurences.models.Activity;
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      jdbcTemplate.query("SELECT id, title, description FROM activity",
        (rs, row) -> new Activity(
          Long.valueOf(rs.getString(1).trim()) ,
          rs.getString(2),
          rs.getString(3)
          )
      ).forEach(activity -> log.info("Found <" + activity + "> in the database."));
    }
  }
}