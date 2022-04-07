import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class acl {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        ArrayList<user> users = new ArrayList<>();
        ArrayList<file> files = new ArrayList<>();

        boolean controlUsers = true;
        boolean controlFiles = true;


        File usersFile = new File("users.txt");
        File filesFile = new File("files.txt");


        try {
            FileInputStream fisUsers = new FileInputStream(usersFile);
            FileInputStream fisFiles = new FileInputStream(filesFile);

            ObjectInputStream oisUsers = new ObjectInputStream(fisUsers);
            ObjectInputStream oisFiles = new ObjectInputStream(fisFiles);

        while (controlUsers){
            try {
                user user = (user) oisUsers.readObject();
                users.add(user);
            }
            catch (EOFException ex1){
                break;
            }
        }


        while (controlFiles){
            try {
                file file = (file) oisFiles.readObject();
                files.add(file);
            }
            catch (EOFException ex1){
                break;
            }
        }

        oisUsers.close();
        oisFiles.close();
        fisUsers.close();
        fisFiles.close();

        }
        catch (FileNotFoundException e){

        }

        while (!Objects.equals(command, "exit")){
            if (Objects.equals(command, "ls")){
                System.out.println("I will try to write out all files and users and their security levels");
                System.out.println("---------------SECURITY LEVELS---------------");
                System.out.println("------ 0-No Security ------ 1-Unclassified ------ 2-Confidential ------ 3-Secret ------- 4-Top Secret -------");
                System.out.println();
                System.out.println("---------------USERS---------------");
                for (user user: users){
                    System.out.println(user);
                }
                System.out.println("---------------FILES---------------");
                for (file file: files){
                    System.out.println(file);
                }
            }
            else if(Objects.equals(command, "user")){
                System.out.println("I will try to create new user.");
                System.out.print("Pass me name of user:");
                String name = sc.nextLine();
                for (user user: users){
                    while (Objects.equals(user.getName(),name)){
                        System.out.println("This username is already taken, please provide new one.");
                        System.out.print("Pass me name of user:");
                        name = sc.nextLine();
                    }
                }
                System.out.println("---------------SECURITY LEVELS---------------");
                System.out.println("------ 0-No Security ------ 1-Unclassified ------ 2-Confidential ------ 3-Secret ------- 4-Top Secret -------");
                System.out.print("Pass me security level of user(1-4, 0 for none):");
                int securityLevel = sc.nextInt();
                while (securityLevel < 0 || securityLevel > 4){
                    System.out.println("Invalid security level. Pass security level in defined range!");
                    System.out.print("Pass me security level of user(1-4, 0 for none):");
                    securityLevel = sc.nextInt();
                }
                users.add(new user(name,securityLevel));
            }
            else if(Objects.equals(command, "touch")){
                System.out.println("I will try to create new file.");
                System.out.print("Pass me name of file:");
                String name = sc.nextLine();
                for (file file: files){
                    while (Objects.equals(file.getName(),name)){
                        if (Objects.equals(file.getType(), "directory")){
                            break;
                        }
                        System.out.println("This username is already taken, please provide new one.");
                        System.out.print("Pass me name of file:");
                        name = sc.nextLine();
                    }
                }
                System.out.println("---------------SECURITY LEVELS---------------");
                System.out.println("------ 0-No Security ------ 1-Unclassified ------ 2-Confidential ------ 3-Secret ------- 4-Top Secret -------");
                System.out.print("Pass me security level of file(1-4, 0 for none):");
                int securityLevel = sc.nextInt();
                while (securityLevel < 0 || securityLevel > 4){
                    System.out.println("Invalid security level. Pass security level in defined range!");
                    System.out.print("Pass me security level of file(1-4, 0 for none):");
                    securityLevel = sc.nextInt();
                }
                files.add(new file(name, "file", securityLevel));
            }
            else if(Objects.equals(command, "mkdir")){
                System.out.println("I will try to create new directory.");
                System.out.print("Pass me name of directory:");
                String name = sc.nextLine();
                for (file directory: files){
                    while (Objects.equals(directory.getName(),name)){
                        if (Objects.equals(directory.getType(), "file")){
                            break;
                        }
                        System.out.println("This username is already taken, please provide new one.");
                        System.out.print("Pass me name of directory:");
                        name = sc.nextLine();
                    }
                }
                System.out.println("---------------SECURITY LEVELS---------------");
                System.out.println("------ 0-No Security ------ 1-Unclassified ------ 2-Confidential ------ 3-Secret ------- 4-Top Secret -------");
                System.out.print("Pass me security level of directory(1-4, 0 for none):");
                int securityLevel = sc.nextInt();
                while (securityLevel < 0 || securityLevel > 4){
                    System.out.println("Invalid security level. Pass security level in defined range!");
                    System.out.print("Pass me security level of directory(1-4, 0 for none):");
                    securityLevel = sc.nextInt();
                }
                files.add(new file(name, "directory", securityLevel));
            }
            else if (Objects.equals(command, "mod")){
                boolean setter = false;
                System.out.println("I will try to set security level of file/directory/user");
                System.out.print("Do you want to modify file(f)/directory(d)/user(u)? :");
                String type = sc.nextLine();
                while (!Objects.equals(type,"f") && !Objects.equals(type,"u") && !Objects.equals(type,"d")){
                    System.out.println("Invalid type of modification!");
                    System.out.print("Do you want to modify file(f)/directory(d)/user(u)? :");
                    type = sc.nextLine();
                }
                if (Objects.equals(type,"f")){
                    System.out.print("Pass me name of file/directory:");
                    String name = sc.nextLine();
                    for (file file: files){
                        if (Objects.equals(file.getName(), name) && Objects.equals(file.getType(),"file")){
                            System.out.println("---------------SECURITY LEVELS---------------");
                            System.out.println("------ 0-No Security ------ 1-Unclassified ------ 2-Confidential ------ 3-Secret ------- 4-Top Secret -------");
                            System.out.print("Pass me NEW security level of file/directory(1-4, 0 for none):");
                            int securityLevel = sc.nextInt();
                            while (securityLevel < 0 || securityLevel > 4){
                                System.out.println("Invalid security level. Pass security level in defined range!");
                                System.out.print("Pass me NEW security level of file/directory(1-4, 0 for none):");
                                securityLevel = sc.nextInt();
                            }
                            file.setSecurityLevel(securityLevel);
                            setter = true;
                        }
                    }
                    if(!setter){
                        System.out.println("Aborting modification, no such a file.");
                    }
                }else if (Objects.equals(type, "d")){
                    System.out.print("Pass me name of directory:");
                    String name = sc.nextLine();
                    for (file directory: files){
                        if (Objects.equals(directory.getName(), name) && Objects.equals(directory.getType(),"directory")){
                            System.out.println("---------------SECURITY LEVELS---------------");
                            System.out.println("------ 0-No Security ------ 1-Unclassified ------ 2-Confidential ------ 3-Secret ------- 4-Top Secret -------");
                            System.out.print("Pass me NEW security level of directory(1-4, 0 for none):");
                            int securityLevel = sc.nextInt();
                            while (securityLevel < 0 || securityLevel > 4){
                                System.out.println("Invalid security level. Pass security level in defined range!");
                                System.out.print("Pass me NEW security level of directory(1-4, 0 for none):");
                                securityLevel = sc.nextInt();
                            }
                            directory.setSecurityLevel(securityLevel);
                            setter = true;
                        }
                    }
                    if(!setter){
                            System.out.println("Aborting modification, no such a directory.");
                    }
                }else if (Objects.equals(type, "u")){
                    System.out.print("Pass me name of user:");
                    String name = sc.nextLine();
                    for (user user: users){
                        if (Objects.equals(user.getName(), name)){
                            System.out.println("---------------SECURITY LEVELS---------------");
                            System.out.println("------ 0-No Security ------ 1-Unclassified ------ 2-Confidential ------ 3-Secret ------- 4-Top Secret -------");
                            System.out.print("Pass me NEW security level of user(1-4, 0 for none):");
                            int securityLevel = sc.nextInt();
                            while (securityLevel < 0 || securityLevel > 4){
                                System.out.println("Invalid security level. Pass security level in defined range!");
                                System.out.print("Pass me NEW security level of user(1-4, 0 for none):");
                                securityLevel = sc.nextInt();
                            }
                            user.setSecurityLevel(securityLevel);
                            setter = true;
                        }
                    }
                    if (!setter){
                        System.out.println("Aborting modification, no such a user.");
                    }
                }
            }
            else if (Objects.equals(command, "zapis")){
                boolean foundUser = false;
                boolean foundFile = false;
                boolean foundDirectory = false;
                System.out.println("I will try to perform write if I can");
                System.out.print("Pass me name of user:");
                String nameUser = sc.nextLine();
                for(user user: users){
                    if (Objects.equals(user.getName(),nameUser)){
                        foundUser = true;
                        System.out.print("Do you want do write into file(f)/directory(d)?:");
                        String type = sc.nextLine();
                        while (!Objects.equals(type,"f") && !Objects.equals(type,"d")){
                            System.out.println("Invalid type of writing file!");
                            System.out.print("Do you want to write into file(f)/directory(d)?:");
                            type = sc.nextLine();
                        }
                        if(Objects.equals(type,"f")){
                            System.out.print("Pass me name of file:");
                            String nameFile = sc.nextLine();
                            for (file file: files){
                                if (Objects.equals(file.getName(),nameFile) && Objects.equals(file.getType(),"file")){
                                    foundFile = true;
                                    if ((user.getSecurityLevel() > 0 && user.getSecurityLevel() <= file.getSecurityLevel()) || (file.getSecurityLevel() == 0 && user.getSecurityLevel() == 0) || file.getSecurityLevel() == 0){
                                        System.out.println("OK");
                                    } else if (user.getSecurityLevel() > file.getSecurityLevel() || user.getSecurityLevel() == 0) {
                                        System.out.println("CHYBA OPRAVNENIA");
                                    }
                                }
                            }
                            if (!foundFile){
                                System.out.println("No such a file!");
                            }
                        }
                        if (Objects.equals(type,"d")){
                            System.out.print("Pass me name of directory:");
                            String nameDirectory = sc.nextLine();
                            for (file directory: files){
                                if (Objects.equals(directory.getName(),nameDirectory) && Objects.equals(directory.getType(),"directory")){
                                    foundDirectory = true;
                                    if ((user.getSecurityLevel() > 0 && user.getSecurityLevel() <= directory.getSecurityLevel()) || (directory.getSecurityLevel() == 0 && user.getSecurityLevel() == 0) || directory.getSecurityLevel() == 0){
                                        System.out.println("OK");
                                    } else if (user.getSecurityLevel() > directory.getSecurityLevel() || user.getSecurityLevel() == 0) {
                                        System.out.println("CHYBA OPRAVNENIA");
                                    }
                                }
                            }
                            if(!foundDirectory){
                                System.out.println("No such a directory!");
                            }
                        }
                    }
                }
                if (!foundUser){
                    System.out.println("No such a user!");
                }

            }
            else if (Objects.equals(command, "citaj")){
                boolean foundUser = false;
                boolean foundFile = false;
                boolean foundDirectory = false;
                System.out.println("I will try to perform read if I can");
                System.out.print("Pass me name of user:");
                String nameUser = sc.nextLine();
                for(user user: users){
                    if (Objects.equals(user.getName(),nameUser)){
                        foundUser = true;
                        System.out.print("Do you want do read from file(f)/directory(d)?:");
                        String type = sc.nextLine();
                        while (!Objects.equals(type,"f") && !Objects.equals(type,"d")){
                            System.out.println("Invalid type of readingfile!");
                            System.out.print("Do you want to read from file(f)/directory(d)?:");
                            type = sc.nextLine();
                        }
                        if(Objects.equals(type,"f")){
                            System.out.print("Pass me name of file:");
                            String nameFile = sc.nextLine();
                            for (file file: files){
                                if (Objects.equals(file.getName(),nameFile) && Objects.equals(file.getType(),"file")){
                                    foundFile = true;
                                    if (user.getSecurityLevel() >= file.getSecurityLevel() || file.getSecurityLevel() == 0 && user.getSecurityLevel() != 0){
                                        System.out.println("OK");
                                    } else if (user.getSecurityLevel() < file.getSecurityLevel() || user.getSecurityLevel() == 0) {
                                        System.out.println("CHYBA OPRAVNENIA");
                                    }
                                }
                            }
                            if (!foundFile){
                                System.out.println("No such a file!");
                            }
                        }
                        if (Objects.equals(type,"d")){
                            System.out.print("Pass me name of directory:");
                            String nameDirectory = sc.nextLine();
                            for (file directory: files){
                                if (Objects.equals(directory.getName(),nameDirectory) && Objects.equals(directory.getType(),"directory")){
                                    foundDirectory = true;
                                    if (user.getSecurityLevel() >= directory.getSecurityLevel() || directory.getSecurityLevel() == 0 && user.getSecurityLevel() != 0){
                                        System.out.println("OK");
                                    } else if (user.getSecurityLevel() < directory.getSecurityLevel() || user.getSecurityLevel() == 0) {
                                        System.out.println("CHYBA OPRAVNENIA");
                                    }
                                }
                            }
                            if(!foundDirectory){
                                System.out.println("No such a directory!");
                            }
                        }
                    }
                }
                if (!foundUser){
                    System.out.println("No such a user!");
                }
            }
            else if (Objects.equals(command, "commit")){
                System.out.println("I will try to commit changes in users and files into .txt files.");
                usersFile = new File("users.txt");
                filesFile = new File("files.txt");

                usersFile.delete();
                filesFile.delete();

                FileOutputStream fosUsers = new FileOutputStream(new File("users.txt"));
                FileOutputStream fosFiles = new FileOutputStream(new File("files.txt"));

                ObjectOutputStream oosUsers = new ObjectOutputStream(fosUsers);
                ObjectOutputStream oosFiles = new ObjectOutputStream(fosFiles);

                for (user user: users){
                    oosUsers.writeObject(user);
                }

                for (file file: files){
                    oosFiles.writeObject(file);
                }

                fosUsers.close();
                fosFiles.close();
                oosUsers.close();
                oosFiles.close();
                System.out.println("Changes were commited successfully.");
            }

            command = sc.nextLine();
        }

    }

}
