package ipca.hrem.com.ResourceManagers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ipca.hrem.com.ObjectResources.Client;

import static ipca.hrem.com.ObjectResources.Client.nameType.apelido;
import static ipca.hrem.com.ObjectResources.Client.nameType.female;
import static ipca.hrem.com.ObjectResources.Client.nameType.male;
import static ipca.hrem.com.ResourceManagers.ClientGenerator.clientType.single;

/**
 * Created by Ferna on 03/01/2018.
 */

public class ClientGenerator {
    Client client;
    ArrayList<Client[]> clients;
    int numbClients;
    int clientsmaxNumb;
    HashMap<Client.nameType, ArrayList<String>> nomes;
    ArrayList<String> nomesMasculinos;
    ArrayList<String> nomesFemininos;
    ArrayList<String> apelidos;
    SpriteBatch spriteBatch;
    Random random;
    int numbRandom;


    enum clientType {single, couple, married, familyOfOne, familyOfTwo, familyOfTree}

    public int getNumbClients() {
        return numbClients;
    }

    public ArrayList<Client[]> getClients() {
        return clients;
    }


    public ClientGenerator() {
        random = new Random();
        numbRandom = random.nextInt(9);
        clientsmaxNumb = 20;
        numbClients = clientsmaxNumb;
        nomes = new HashMap<Client.nameType, ArrayList<String>>();
        spriteBatch = new SpriteBatch();//Colocar as sprites dentro
        nomesMasculinos = new ArrayList<String>();
        PutNamesMasculino(nomesMasculinos);
        nomesFemininos = new ArrayList<String>();
        PutNamesFeminino(nomesFemininos);
        apelidos = new ArrayList<String>();
        PutNamesApelidos(apelidos);
        nomes.put(male, nomesMasculinos);
        nomes.put(female, nomesFemininos);
        nomes.put(apelido, apelidos);
        clients = new ArrayList<Client[]>(clientsmaxNumb);

        for (int i = 0; i < 100; i++) {
            clients.add((ClientCreator(single, nomes)));
        }
    }

    public void update(float deltaTime) {
        for (Client[] c : clients
                ) {
            for (Client client : c
                    ) {
                client.update(deltaTime);
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (Client[] c : clients) {
            for (Client client : c) {
                client.render(spriteBatch);
            }
        }
    }

    private void PutNamesApelidos(ArrayList<String> apelidos) {
        apelidos.add("Solo");
        apelidos.add("Skywalker");
        apelidos.add("Baggins");
        apelidos.add("Gamgee");
        apelidos.add("Elessar");
        apelidos.add("Hut");
        apelidos.add("Fett");
        apelidos.add("Calrissian");
        apelidos.add("Campbell");
        apelidos.add("Fox");
    }

    private void PutNamesFeminino(ArrayList<String> nomesFemininos) {
        nomesFemininos.add("Leia");
        nomesFemininos.add("Padme");
        nomesFemininos.add("Bulma");
        nomesFemininos.add("Meryl");
        nomesFemininos.add("Naomi");
        nomesFemininos.add("Maria");
        nomesFemininos.add("Rey");
        nomesFemininos.add("Elanor");
        nomesFemininos.add("Anna");
        nomesFemininos.add("Galadriel");
    }

    private void PutNamesMasculino(ArrayList<String> nomesMasculinos) {
        nomesMasculinos.add("Luke");
        nomesMasculinos.add(("Han"));
        nomesMasculinos.add(("Aragorn"));
        nomesMasculinos.add(("Legolas"));
        nomesMasculinos.add(("Frodo"));
        nomesMasculinos.add(("SamWise"));
        nomesMasculinos.add(("Snake"));
        nomesMasculinos.add(("Lando"));
        nomesMasculinos.add(("Jabba"));
        nomesMasculinos.add(("Boba"));
    }

    private Client[] ClientCreator(clientType clientType, HashMap<Client.nameType, ArrayList<String>> dicionarioNomes) {
        int numeroClientes = 0;
        Client[] clients = null;
        switch (clientType) {
            case single:
                //Meter isto dentro duma função
                numeroClientes = 1;
                clients = new Client[numeroClientes];
                numbRandom = random.nextInt(9);
                Vector2 posicaoInicial= new Vector2(randomCorner(numbRandom));
                client = new Client(new Vector2(posicaoInicial), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, numbRandom, dicionarioNomes));
                clients[0] = client;
                break;
            case couple:
                numeroClientes = 2;
                clients = new Client[numeroClientes];
                numbRandom = random.nextInt(9);
                Vector2 posicaoInicial2= new Vector2(randomCorner(numbRandom));
                client = new Client(posicaoInicial2, (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, numbRandom, dicionarioNomes));
                clients[0] = client;
                numbRandom = random.nextInt(9);
                client = new Client(new Vector2(posicaoInicial2.x+1, posicaoInicial2.y), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, numbRandom, dicionarioNomes));
                clients[1] = client;
                break;
            case married:
                numeroClientes = 2;
                clients = new Client[numeroClientes];
                numbRandom = random.nextInt(9);
                Vector2 posicaoInicial3= new Vector2(randomCorner(numbRandom));
                client = new Client(posicaoInicial3, (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, numbRandom, dicionarioNomes));
                clients[0] = client;
                int apelidoPosition = numbRandom;
                numbRandom = random.nextInt(9);
                client = new Client(new Vector2(posicaoInicial3.x+1, posicaoInicial3.y), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPosition, dicionarioNomes));
                clients[1] = client;
                break;
            case familyOfOne:
                numeroClientes = 3;
                clients = new Client[numeroClientes];
                numbRandom = random.nextInt(9);
                Vector2 posicaoInicial4= new Vector2(randomCorner(numbRandom));
                client = new Client(posicaoInicial4, (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, numbRandom, dicionarioNomes));
                int apelidoPositionForChild = numbRandom;
                clients[0] = client;
                numbRandom = random.nextInt(9);
                client = new Client(new Vector2(posicaoInicial4.x+1, posicaoInicial4.y), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild, dicionarioNomes));
                clients[1] = client;
                numbRandom = random.nextInt(9);
                client = new Client(new Vector2(posicaoInicial4.x+1, posicaoInicial4.y+1), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild, dicionarioNomes));
                clients[2] = client;
                break;
            case familyOfTwo:
                numeroClientes = 4;
                clients = new Client[numeroClientes];
                numbRandom = random.nextInt(9);
                Vector2 posicaoInicial5= new Vector2(randomCorner(numbRandom));
                client = new Client(posicaoInicial5, (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, numbRandom, dicionarioNomes));
                int apelidoPositionForChild2 = numbRandom;
                clients[0] = client;
                numbRandom = random.nextInt(9);
                client = new Client(new Vector2(posicaoInicial5.x+1, posicaoInicial5.y), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild2, dicionarioNomes));
                clients[1] = client;
                numbRandom = random.nextInt(9);
                client = new Client(new Vector2(posicaoInicial5.x+1, posicaoInicial5.y+1), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild2, dicionarioNomes));
                clients[2] = client;
                client = new Client(new Vector2(posicaoInicial5.x, posicaoInicial5.y+1), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild2, dicionarioNomes));
                clients[3] = client;
                break;
            case familyOfTree:
                numeroClientes = 5;
                clients = new Client[numeroClientes];
                numbRandom = random.nextInt(9);
                Vector2 posicaoInicial6= new Vector2(randomCorner(numbRandom));
                client = new Client(posicaoInicial6, (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, numbRandom, dicionarioNomes));
                int apelidoPositionForChild3 = numbRandom;
                clients[0] = client;
                numbRandom = random.nextInt(9);
                client = new Client(new Vector2(posicaoInicial6.x+1, posicaoInicial6.y), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild3, dicionarioNomes));
                clients[1] = client;
                numbRandom = random.nextInt(9);
                client = new Client(new Vector2(posicaoInicial6.x+1, posicaoInicial6.y+1), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild3, dicionarioNomes));
                clients[2] = client;
                client = new Client(new Vector2(posicaoInicial6.x, posicaoInicial6.y+1), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild3, dicionarioNomes));
                clients[3] = client;
                client = new Client(new Vector2(posicaoInicial6.x+2, posicaoInicial6.y), (float) 1.0, numbRandom, RandomGender(numbRandom));//Fazer uma random position para o render.
                client.setName(DefineGenderName(RandomGender(numbRandom), numbRandom, dicionarioNomes) + DefineGenderName(apelido, apelidoPositionForChild3, dicionarioNomes));
                clients[4] = client;
                break;
        }
        return clients;
    }


    private String DefineGenderName(Client.nameType genderName, int numbRandom, HashMap<Client.nameType, ArrayList<String>> dicionarioNomes) {
        ArrayList<String> nomes = new ArrayList<String>();
        switch (genderName) {
            case male:
                nomes = GetLista(dicionarioNomes, male);
                break;
            case female:
                nomes = GetLista(dicionarioNomes, female);
                break;
            case apelido:
                nomes = GetLista(dicionarioNomes, apelido);
                break;
        }
        return (nomes.get(numbRandom) + "\n");
    }

    private ArrayList<String> GetLista(HashMap<Client.nameType, ArrayList<String>> names, Client.nameType genderName) {
        ArrayList<String> listaNomes = new ArrayList<String>();
        listaNomes = names.get(genderName);
        return listaNomes;
    }

    private Client.nameType RandomGender(int numbRandom) {

        Client.nameType nameType = male;
        switch ((numbRandom / 10)) {
            case 0:
                nameType = male;
                break;
            case 1:
                nameType = female;
                break;
        }
        return nameType;
    }
    private Vector2 randomCorner(int numbRandom)
    {
        Random random= new Random();
        Vector2 posicaoMapa= Vector2.Zero;
        if(numbRandom>=0&&numbRandom<2)
            posicaoMapa= new Vector2(0+random.nextInt(8), 0+random.nextInt(15));
        if(numbRandom>=2&&numbRandom<4)
            posicaoMapa= new Vector2(0+random.nextInt(13), 14+random.nextInt(15));
        if(numbRandom>=4&&numbRandom<6)
            posicaoMapa= new Vector2(14+random.nextInt(15), 20+random.nextInt(9));
        if(numbRandom>=6&&numbRandom<9)
            posicaoMapa= new Vector2(9+random.nextInt(20), 0+random.nextInt(5));
        return  posicaoMapa;
    }
}
