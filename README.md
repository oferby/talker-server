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



