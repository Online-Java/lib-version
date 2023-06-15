package uz.online.libversion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author a.ergashev
 * Date: 6/15/2023
 * Time: 5:46 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestResponse {
    private int port;
    private String instance;
    private String message;
}
