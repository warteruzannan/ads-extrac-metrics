# Advanced Detect Smell(ADS) Extrac Metrics :mag_right: 

[![Maven Central status](https://img.shields.io/maven-central/v/org.commonmark/commonmark.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.commonmark%22)
[![javadoc](https://www.javadoc.io/badge/org.commonmark/commonmark.svg?color=blue)](https://www.javadoc.io/doc/org.commonmark/commonmark)


ADS-Extract-Metrics is lib used by InSet tool to extract metrics from oriented objected systems. 
Intelligent Smell Detector (InSet) is a tool created by Warteruzannan Soyer Cunha for detecting architectural smells using machine learning. It was created within the AdvanSE laboratory of the Universidade Federal de São Carlos (UFSCar).

(ADS) Extrac Metrics uses the libs JDpende and CKJM Extend behind de scenes. 

### Requiriments 
- Java +8

### Using JAR as CLI

`java -jar ads-extract-metrics-0.0.4.jar <path of java project> -p <path to output files>`

### Example of Output 

Output shows the metrics by packages and classes. 

| Name of metrics | Class or package | Type of Value |
| ------ | ------ |------ |
| AMC | class | Numeric |
| Name | class | String |
| CAM | class | Numeric |
| CBM | class | Numeric |
| CBO | class | Numeric |
| Ca | class | Numeric |
| Ce | class | Numeric |
| DAM | class | Numeric |
| DIT | class | Numeric |
| IC | class | Numeric |
| LCOM | class | Numeric |
| LCOM3 | class | Numeric |
| LOC | class | Numeric |
| MFA | class | Numeric |
| MOA | class | Numeric |
| NOC | class | Numeric |
| NPM | class | Numeric |
| RFC | class | Numeric |
| WMC | class | Numeric |
| abstractClassCount | package | Numeric |
| abstractness | package | Numeric |
| afferentCoupling | package | Numeric |
| afferents | package | Numeric |
| classCount | package | Numeric |
| concreteClassCount | package | Numeric |
| containsCycle | package | Numeric |
| distance | package | Numeric |
| efferentCoupling | package | Numeric |
| efferents | package | Numeric |
| instability | package | Numeric |
| volatility | package | Numeric |

```
{
  "classes": [
    {
      "CC": 2,
      "LOC": 14,
      "Ce": 2,
      "package": "com.controllers",
      "NOC": 0,
      "MFA": 0.0,
      "NPM": 2,
      "WMC": 2,
      "DIT": 1,
      "RFC": 4,
      "CAM": 0.6666666666666666,
      "CBM": 0,
      "LCOM3": 0.0,
      "CBO": 3,
      "DAM": 1.0,
      "LCOM": 0,
      "AMC": 5.5,
      "name": "com.controllers.ConsultaController",
      "IC": 0,
      "Ca": 1,
      "MOA": 1
    }    
  ],
  "name": "/home/warteruzannan/Workspace/consulta/conulta/",
  "packages": [
    {
      "classes": [
        {
          "CC": 2,
          "LOC": 14,
          "Ce": 2,
          "package": "com.controllers",
          "NOC": 0,
          "MFA": 0.0,
          "NPM": 2,
          "WMC": 2,
          "DIT": 1,
          "RFC": 4,
          "CAM": 0.6666666666666666,
          "CBM": 0,
          "LCOM3": 0.0,
          "CBO": 3,
          "DAM": 1.0,
          "LCOM": 0,
          "AMC": 5.5,
          "name": "com.controllers.ConsultaController",
          "IC": 0,
          "Ca": 1,
          "MOA": 1
        }
      ],
      "name": "com.controllers",
      "dependents": ["com.main"],
      "metrics": {
        "efferentCoupling": 2,
        "concreteClassCount": 1,
        "afferentCoupling": 1,
        "distance": 0.3333333,
        "instability": 0.6666667,
        "afferents": 1,
        "efferents": 2,
        "volatility": 1,
        "abstractClassCount": 0,
        "containsCycle": 0,
        "abstractness": 0.0
      },
      "dependencies": ["com.services", "com.models"]
    }    
  ]
}


```




## Development Enviroment 
### Install dependencies using Mavem
Execute `maven clean`


### Build:
To build it tool execute the file `build.sh`


### You can cite this using 

```
@INPROCEEDINGS{warteruzannan2020,
  AUTHOR="Warteruzannan Cunha and Guisella Angulo and Valter Camargo",
  TITLE="InSet: A Tool to Identify Architecture Smells Using Machine Learning",
  BOOKTITLE="34th Brazilian Symposium on Software Engineering (SBES '20), October 21--23, 2020, Natal, Brazil",
  DAYS="19-23",
  MONTH="oct",
  YEAR="2020",
  KEYWORDS="Software Maintenance; Software Quality",
  DOI={10.1145/3422392.3422507},
  ISBN={978-1-4503-8753-8/20/09},
}
```

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
