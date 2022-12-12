package tn.innofab.test_technique.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {

    private long productId;
    private String name;
    private int quantity;
    private float stockValue;
}
