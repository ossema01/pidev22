package tn.esprit.wellbeing.modules.occurences.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
	// Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
