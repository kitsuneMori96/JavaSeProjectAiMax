package com.mori.MovieInformationManagementModule;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class MovieOperator {

    private final ArrayList<Movie> movies = new ArrayList<>();

    public void start(){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("请输入要进行的操作");
            System.out.println("1.添加电影");
            System.out.println("2.删除电影");
            System.out.println("3.查找电影");
            System.out.println("4.删除演员电影");
            System.out.println("5.显示所有电影");
            System.out.println("6.退出");
            try{
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        addMovie();
                        break;
                    case 2:
                        removeMovie();
                        break;
                    case 3:
                        findMovie();
                        break;
                    case 4:
                        removeMoviesByActor();
                        break;
                    case 5:
                        showAllMovies();
                        break;
                    case 6:
                        flag = false;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入");
                        break;
                }
            }
            catch (RuntimeException e) {
                e.printStackTrace();
                System.out.println("输入格式错误，请重新输入");
            }
        }

    }

    public void addMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入电影名称");
        String title = sc.nextLine();
        System.out.println("请输入演员 (多个演员用空格分隔)");
        String[] actorsString = sc.nextLine().split(" ");
        ArrayList<String> actors = new ArrayList<>(Arrays.stream(actorsString).toList());
        movies.add(new Movie(title, actors));
    }

    public void removeMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的电影名称");
        String title = sc.nextLine();
        movies.removeIf(movie -> movie.getTitle().equals(title));
    }

    public void findMovie(){
        System.out.println("请输入要查找的电影名称");
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                System.out.println(movie.getTitle() + "演员: " + movie.getActors());
            }
        }
    }

    public void removeMoviesByActor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的演员名称");
        String actor = sc.nextLine();
        Iterator<Movie> iterator = movies.iterator();
        while(iterator.hasNext()){
            Movie movie = iterator.next();
            ArrayList<String> actors = movie.getActors();
            for(String act : actors){
                if(act.equals(actor)){
                    iterator.remove();
                }
            }
        }
    }

    public void showAllMovies() {
        if(movies.isEmpty()){
            System.out.println("现在没有电影在库中");
            return;
        }
        for (Movie movie : movies) {
            System.out.println(movie.getTitle() + "演员: " + movie.getActors());
        }
    }
}

class test {
    static void main(String[] args) {
        MovieOperator movieOperator = new MovieOperator();
        movieOperator.start();
    }

}