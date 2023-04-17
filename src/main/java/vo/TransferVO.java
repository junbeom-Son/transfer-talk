package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferVO {
	
	private int transfer_id;
	private String player_position;
	private int previous_team_id;
	private int new_team_id;
	private int transfer_year;
	private String fee;
	private int player_id;
	private int age;
}