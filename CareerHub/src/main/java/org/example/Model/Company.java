package org.example.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Company {
    private int companyID;
    private String companyName;
    private String location;
}
