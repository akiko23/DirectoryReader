import java.io.*;

import java.util.*;

public class ReadingFiles {
    private static int CountFiles;
    static Scanner scanner = new Scanner(System.in);

    private static String Phrase = null;

    private static String Phrase2;

    private static String Phrase3;

    private static String FileEndName;


    public static void main(String[] args) {
        Thread timesleep = new Thread();


        System.out.print("Disk: ");
        String disk = scanner.nextLine() + "://";

        System.out.print("Directory: ");
        String directory = scanner.nextLine();

        String way = disk + directory;
        File file = new File(way);

        if (file.isDirectory()) {
            System.out.print("File Extension: ");
            FileEndName = scanner.nextLine();

            if (FileEndName.equals(".txt")) {
                System.out.println("Do you want to read txt files?(1 - Yes; 2 - No; 3 - get only ways for files)");
                Phrase = scanner.nextLine();
            }

            if (FileEndName.equals(".htm") || FileEndName.equals(".docx") || FileEndName.equals(".pdf") || FileEndName.equals(".exe")) {
                System.out.println("Do you want to get file paths(4 - Yes; 5 - No)?");
                Phrase2 = scanner.nextLine() + "\n";
            }


        }
        ReadingDirectories(file, new ArrayList<>());

        System.out.println("Do you want check files size?(1 - Yes; 2 - No )");
        Phrase3 = scanner.nextLine();

        ReadFileSize(file);
    }

    private static void ReadingDirectories(File file, List<File> filelist) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files != null) {
                for (File rootfile : files) {
                    if (rootfile.isDirectory()) {
                        ReadingDirectories(rootfile, filelist);
                        filelist.add(rootfile);
                    }
                    if (rootfile.isFile()) {

                        if (rootfile.getAbsolutePath().endsWith(FileEndName)) {
                            CountFiles++;

                            if (Phrase != null) {

                                if (Phrase.equals("1")) {
                                    ReadingFile(rootfile);

                                } else {
                                    if (Phrase.equals("2")) {
                                        break;
                                    }

                                    if (Phrase.equals("3")) {
                                        System.out.println(CountFiles + " file: " + rootfile.getAbsolutePath());
                                    }
                                }
                            }
                            if (Phrase2 != null) {

                                if (Phrase2.equals("4")) {
                                    System.out.println(CountFiles + " file: " + rootfile.getAbsolutePath() + "\n");
                                }

                                if (Phrase2.equals("5")) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void ReadFileSize(File file){
        if (Phrase3 != null) {

            if (Phrase3.equals("1")) {
                getFilesWeight(file);
            } else if (Phrase3.equals("2")) {
                System.out.println("break");
            }
        }
    }


    private static void ReadingFile(File file) {
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);

                int code;

                StringBuilder stringBuilder = new StringBuilder();

                if (file.getAbsolutePath().endsWith(".txt")) {
                    for (; ; ) {
                        code = fileInputStream.read();

                        if (code < 0) {
                            break;
                        }

                        char ch = (char) code;

                        stringBuilder.append(ch);
                    }
                    System.out.println(stringBuilder);
                }

            } catch (FileNotFoundException e) {
                System.out.println("Вы некорректно ввели файл");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        private static long getFilesWeight (File path){
            double kilobytes;
            double megabytes;
            double gigabytes;

            String word = null;
            double eve = 0;


            long sum = 0;
            File[] files = path.listFiles();


            for (File file : files) {
                if (file.isDirectory()) {
                    sum += getFilesWeight(file);
                }

                sum += file.length();

                if (sum > 10000) {
                    kilobytes = sum / 1024;
                    word = "kb";
                    eve = kilobytes;
                }
                if (sum > 1000000) {
                    megabytes = sum / 1024 / 1024;
                    word = "mb";
                    eve = megabytes;
                }

                if (sum > 1000000000) {
                    gigabytes = sum / 1024 / 1024 / 1024;
                    word = "gb";
                    eve = gigabytes;
                }
            }
            System.out.println("File size: " + eve + " " + word);

            return sum;

        }
    }

