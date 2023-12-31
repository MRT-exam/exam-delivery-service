package com.mtgo.exam.deliveryservice.graphql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraphqlRequestBody {

    private String query;
    private Map<String, Object> variables;

    public String toString() {
        return "GraphqlRequestBody{" +
                "query='" + query + '\'' +
                ", variables=" + variables +
                '}';
    }
}
