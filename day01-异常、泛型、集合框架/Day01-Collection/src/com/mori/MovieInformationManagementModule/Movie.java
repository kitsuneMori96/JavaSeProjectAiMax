package com.mori.MovieInformationManagementModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Movie {
    private String title;
    private ArrayList<String> actors;


}
