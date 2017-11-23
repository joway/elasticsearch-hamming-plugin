# elasticsearch-hamming-plugin

## Usage

1. cd /tmp 
2. wget https://github.com/joway/elasticsearch-hamming-plugin/raw/master/releases/elasticsearch-hamming-0.0.1.zip
3. /usr/share/elasticsearch/bin/elasticsearch-plugin  install  file://`pwd`/elasticsearch-hamming-0.0.1.zip
4. echo "script.inline: true" >> /etc/elasticsearch/elasticsearch.yml && echo "script.stored: true" >> /etc/elasticsearch/elasticsearch.yml
5. POST /index_name/_search :
    ```json
    {
      "query": {
        "function_score": {
          "query": {
            "match_all": {}
          },
          "functions": [
            {
              "script_score": {
                "script": {
                  "inline": "hamming_distance_native",
                  "lang": "native",
                  "params": {
                    "hash": "3dd3c609f30cd16c",
                    "field": "phash"
                  }
                }
              }
            }
          ]
        }
      }
    }
    ```
