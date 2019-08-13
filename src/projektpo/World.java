/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;

import java.lang.reflect.Constructor;
import java.util.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JPanel;

/**
 *
 * @author Basia
 */

public class World {

    private Window window;
    private int time;
    private Organism[][] Field;
    private ArrayList<Organism> organisms;
    private ArrayList<String> comments;
    final int size = 20;

    public World() {

        Field = new Organism[size][];
        comments = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            Field[i] = new Organism[size];
            for (int k = 0; k < size; k++) {
                Field[i][k] = null;
            }
        }
        time = 0;
        organisms = new ArrayList<Organism>();
        for (int i = 0; i < size / 10; i++) {
            createRandom(Fox.class);
        }
        for (int i = 0; i < size / 10; i++) {
            createRandom(Wolf.class);
        }
        createRandom(Human.class);
        for (int i = 0; i < size / 10; i++) {
            createRandom(Sheep.class);
        }
        for (int i = 0; i < size / 10; i++) {
            createRandom(Antylope.class);
        }
        for (int i = 0; i < size / 10; i++) {
            createRandom(Turtle.class);
        }
        for (int i = 0; i < size / 10; i++) {
            createPlant(Grass.class);
        }
        for (int i = 0; i < size / 10; i++) {
            createPlant(Dandelion.class);
        }
        for (int i = 0; i < size / 10; i++) {
            createPlant(Guarana.class);
        }
        for (int i = 0; i < size / 10; i++) {
            createPlant(DeadlyNightshade.class);
        }
        for (int i = 0; i < size / 10; i++) {
            createPlant(SosnowskiBorsh.class);
        }
        window = new Window(this);
        window.show();
    }

    private <T> void createRandom(Class<T> class2create) {
        int X, Y;
        Random randomGenerator = new Random();
        do {
            X = randomGenerator.nextInt(size);
            Y = randomGenerator.nextInt(size);
        } while (Field[Y][X] != null);
        try {
            Constructor ctor = class2create.getDeclaredConstructor(int.class, int.class, int.class, World.class);
            T animal = (T) ctor.newInstance(X, Y, time, this);
            organisms.add((Organism) animal);
            Field[Y][X] = (Organism) animal;
            time += 1;
        } catch (Exception e) {
             e.printStackTrace();
             System.out.println(e);
        }
    }

    private <T> void createPlant(Class<T> class2create) {
        int X, Y;
        Random randomGenerator = new Random();
        do {
            X = randomGenerator.nextInt(size);
            Y = randomGenerator.nextInt(size);
        } while (Field[Y][X] != null);
        try {
            Constructor ctor = class2create.getDeclaredConstructor(int.class, int.class, World.class);
            T animal = (T) ctor.newInstance(X, Y, this);
            organisms.add((Organism) animal);
            Field[Y][X] = (Organism) animal;
            time += 1;
        } catch (Exception e) {
             e.printStackTrace();
             System.out.println(e);
        }
    }

    public Organism GetField(int y, int x) {
        return (Field[y][x]);
    }

    public void moveAnimal(int prevY, int prevX, int newY, int newX) {
        Organism tmp = Field[prevY][prevX];
        Field[prevY][prevX] = Field[newY][newX];
        Field[newY][newX] = tmp;
    }

    public void deleteOrganism(int y, int x) {
        organisms.remove(Field[y][x]);
        Field[y][x] = null;
    }

    public void setField(int y, int x, Organism org) {
        Field[y][x] = org;
    }

    public void addComment(String word) {
        comments.add(word);
    }

    public void commentClear() {
        comments.clear();
    }

    public int getTime() {
        return time;
    }

    public void increaseTime() {
        time++;
    }

    public void addOrganism(Organism animal) {
        organisms.add(animal);
    }

    public void sortOrganisms() {
        OrganismCompare organismCompare = new OrganismCompare();
        Collections.sort(organisms, organismCompare);
    }

    public void doRound(int dir) {
        int i = 0;
        for (int k = 0; k < organisms.size();) {
            i = organisms.get(k).action(dir);
            if (i == 0 || i == 3 || i == 4) {
                k++;
            }
        }
    }

    public void drawWorld(int i, JPanel p) {
     organisms.get(i).draw(p);
    }

    public void save() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("java.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("%d\r\n%d\r\n%d\r\n", size, time, organisms.size());
            for (int i = 0; i < organisms.size(); i++) {
                printWriter.printf("%d\r\n%d\r\n%d\r\n%d\r\n%d\r\n", organisms.get(i).GetId(), organisms.get(i).getInniciative(), organisms.get(i).GetAge(), organisms.get(i).GetY(), organisms.get(i).GetX());
            }
            printWriter.close();
        } catch (IOException e) {
             e.printStackTrace();
             System.out.println(e);

        } finally {

            try {

                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ex) {            
                ex.printStackTrace();
                System.out.println(ex);
                
            }
        }
    }

    public void load() {
        comments.clear();
        organisms.clear();
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                Field[i][k] = null;
            }
        }
        int sizeOfWorld, organismsSize, id, inniciative, age, Y, X;
        String line;
        Path path = FileSystems.getDefault().getPath("java.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            line = reader.readLine();
            sizeOfWorld = Integer.parseInt(line);
            line = reader.readLine();
            time = Integer.parseInt(line);
            line = reader.readLine();
            organismsSize = Integer.parseInt(line);

            for (int i = 0; i < organismsSize; i++) {
                line = reader.readLine();
                id = Integer.parseInt(line);
                line = reader.readLine();
                inniciative = Integer.parseInt(line);
                line = reader.readLine();
                age = Integer.parseInt(line);
                line = reader.readLine();
                Y = Integer.parseInt(line);
                line = reader.readLine();
                X = Integer.parseInt(line);
                if (inniciative == 0) {
                    switch (id) {
                        case 1: {
                            Grass organism = new Grass(X, Y, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                        case 2: {
                            Dandelion organism = new Dandelion(X, Y, this);
                            organisms.add(organism);
                            break;
                        }
                        case 3: {
                            Guarana organism = new Guarana(X, Y, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                        case 4: {
                            DeadlyNightshade organism = new DeadlyNightshade(X, Y, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                        case 5: {
                            SosnowskiBorsh organism = new SosnowskiBorsh(X, Y, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                    }
                } else {
                    switch (id) {
                        case 0: {
                        }
                        case 1: {
                            Wolf organism = new Wolf(X, Y, age, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                        case 2: {
                            Sheep organism = new Sheep(X, Y, age, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                        case 3: {
                            Fox organism = new Fox(X, Y, age, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                        case 4: {
                            Turtle organism = new Turtle(X, Y, age, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                        case 5: {
                            Antylope organism = new Antylope(X, Y, age, this);
                            organisms.add(organism);
                            Field[Y][X] = organism;
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
             e.printStackTrace();
             System.out.println(e);

        }

    }

    public void turnOnSkill() {
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i) instanceof Human) {
                organisms.get(i).turnOnSkill();
            }
        }
    }

    public <T> void createNotRandom(Class<T> class2create, int Y, int X) {
        try {
            Constructor ctor = class2create.getDeclaredConstructor(int.class, int.class, int.class, World.class);
            T animal = (T) ctor.newInstance(X, Y, time, this);
            organisms.add((Organism) animal);
            Field[Y][X] = (Organism) animal;
            time += 1;
        } catch (Exception e) {
             e.printStackTrace();
             System.out.println(e);
        }
    }

    public <T> void createPlantNotRandom(Class<T> class2create, int Y, int X) {

        try {
            Constructor ctor = class2create.getDeclaredConstructor(int.class, int.class, World.class);
            T animal = (T) ctor.newInstance(X, Y, this);
            organisms.add((Organism) animal);
            Field[Y][X] = (Organism) animal;
            time += 1;
        } catch (Exception e) {
             e.printStackTrace();
             System.out.println(e);
        }
    }
    
    public int getOrganismX(int i){
         return organisms.get(i).GetX();
    }
    
    public int getOrganismY(int i){
        return organisms.get(i).GetY();
    }

    public int getOrganismSize(){
        return organisms.size();
    }
    
        public int getCommentsSize(){
        return comments.size();
    }
    public String getComment(int i){
        return comments.get(i);
    }
}
