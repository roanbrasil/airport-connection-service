### Requirements

You must install Java 10 and Maven 3.6.1 at least

### Running

To run the content, first at all you should

```
mvn clean install
```

Please run the command bellow.

```
mvn spring-boot:run
```

### Consuming API

If you wanna consume the api.

```
http://localhost:8080/route/origin/CZX/destination/SFO

```

the return might be something such as

````
[
    {
        "Name": "Changzhou Benniu Airport",
        "City": "Changzhou",
        "Country": "China",
        "IATA 3": "CZX",
        "Latitute": "31.919701",
        "Longitude": "119.778999"
    },
    {
        "Name": "Shenzhen Bao'an International Airport",
        "City": "Shenzhen",
        "Country": "China",
        "IATA 3": "SZX",
        "Latitute": "22.63929939",
        "Longitude": "113.810997"
    },
    {
        "Name": "Beijing Capital International Airport",
        "City": "Beijing",
        "Country": "China",
        "IATA 3": "PEK",
        "Latitute": "40.08010101",
        "Longitude": "116.5849991"
    },
    {
        "Name": "San Francisco International Airport",
        "City": "San Francisco",
        "Country": "United States",
        "IATA 3": "SFO",
        "Latitute": "37.61899948",
        "Longitude": "-122.375"
    }
]
