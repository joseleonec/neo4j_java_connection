package neo4j_java_connection;

import java.util.List;
import java.util.Scanner;

import org.neo4j.driver.v1.util.Pair;
import org.neo4j.driver.v1.*;
import static org.neo4j.driver.v1.Values.parameters;

public class BlankSandbox {

    public static void main(String... args) {
        Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
        String IP_SERVER, PORT, USER, PASSWORD, URL;
        IP_SERVER = "54.237.28.79";
        PORT = "34995";
        USER = "neo4j";
        PASSWORD = "pint-speeder-sets";
        URL = "bolt://" + IP_SERVER + ":" + PORT;
        Driver driver = GraphDatabase.driver(URL, AuthTokens.basic(USER, PASSWORD), noSSL); // <password>
        try (Session session = driver.session()) {
            String cypherQuery = "match (p:Parroquia) RETURN p.nombre, p.idParroquia";
            StatementResult result = session.run(cypherQuery, parameters());
            while (result.hasNext()) {
//                System.out.println(result.next().get("p.nombre"));
                List<Pair<String, Value>> fields = result.next().fields();
                System.out.println(fields.get(1).value().toString() + "\t" + fields.get(0).value().toString());
            }
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Ingrese un letra para terminar...");
            String tecla = myObj.next();  // Read user input
            System.exit(0);
        }
    }
}


//            String cypherQuery
//                    = "MATCH (n) "
//                    + "RETURN id(n) AS id";
