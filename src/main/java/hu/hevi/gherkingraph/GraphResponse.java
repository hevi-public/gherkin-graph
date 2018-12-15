package hu.hevi.gherkingraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphResponse {

    private List<Step> nodes;
    private List<Edge> edges;
}
