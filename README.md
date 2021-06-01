##elastic-search docker image

###install and run:
sudo docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.12.1

## NEO4j server

### install and run:
sudo docker run \
--name neo4j \
-p7474:7474 -p7687:7687 \
-d \
-v $HOME/neo4j/data:/data \
-v $HOME/neo4j/logs:/logs \
-v $HOME/neo4j/import:/var/lib/neo4j/import \
-v $HOME/neo4j/plugins:/plugins \
--env NEO4J_AUTH=neo4j/test \
neo4j:latest

## related links

### Knowledge Graph
https://towardsdatascience.com/language-models-are-open-knowledge-graphs-but-are-hard-to-mine-13e128f3d64d

### PyTorch BigGraph
https://towardsdatascience.com/extracting-knowledge-from-knowledge-graphs-e5521e4861a0
https://arxiv.org/pdf/1903.12287.pdf

### CoreNLP
https://stanfordnlp.github.io/CoreNLP/

## Ontology

https://www.w3.org/wiki/Ontology_repositories

### Neo4j 
https://neo4j.com/developer/graph-data-science/build-knowledge-graph-nlp-ontologies/#import-onologies

### Network Ontology
https://intranet.icar.cnr.it/wp-content/uploads/2016/11/TechReport-03_22.pdf

### Wikidata:SPARQL query service
https://www.wikidata.org/wiki/Wikidata:SPARQL_query_service

### Spell Checker
http://theautomatic.net/2019/12/10/3-packages-to-build-a-spell-checker-in-python/

### Tools

### REL
https://github.com/informagi/REL

### spaCy
https://spacy.io/


