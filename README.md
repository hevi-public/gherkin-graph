### What's this

Gherkin graph takes Cucumber feature files and displays them in a graph for better visual understanding. 

### Building and running

`mvn clean package`

`java -jar -Dfeature.location=/your/path/to/features,/multiple/with/comma -Dserver.port=8080 target/gherkin-graph-0.0.1-SNAPSHOT.jar`


### Screenshots

Default view:
![default view](doc_assets/default_view.png "Default View")

Scenario selected:
![scenario selected view](doc_assets/scenario_selected.png "Scenario selected")

Step selected
![step selected view](doc_assets/step_selected.png "Step selected")

### With the usage of

http://visjs.org/

jQuery