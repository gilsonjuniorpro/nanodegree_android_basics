package com.musicalstructure.br.util;

/**
 * Created by gilsonjuniorpro on 11/04/17.
 */

public class Util {

    public static String getSongFromArtist(String artist, int id){
        String song = null;

        if(artist.equals("The Cranberries")){
            switch (id){
                case 1 : song = "Zombie"; break;
                case 2 : song = "Linger"; break;
                case 3 : song = "Just My Imagination"; break;
                case 4 : song = "Dreams"; break;
                case 5 : song = "Animal Instinct"; break;
                case 6 : song = "Dreaming My Dreams"; break;
                default : song = "Zombie"; break;
            }
        }else if(artist.equals("Lana Del Rey")){
            switch (id){
                case 1 : song = "Dark Paradise"; break;
                case 2 : song = "Love"; break;
                case 3 : song = "Born To Die"; break;
                case 4 : song = "Ride"; break;
                case 5 : song = "Sad Girl"; break;
                case 6 : song = "Gods And Monsters"; break;
                default : song = "Dark Paradise"; break;
            }
        }else if(artist.equals("John Legend")){
            switch (id){
                case 1 : song = "All Of Me"; break;
                case 2 : song = "Love Me Now"; break;
                case 3 : song = "Ordinary People"; break;
                case 4 : song = "Darkness And Light"; break;
                case 5 : song = "Save Room"; break;
                case 6 : song = "One Woman Man"; break;
                default : song = "All Of Me"; break;
            }
        }else if(artist.equals("Passenger")){
            switch (id){
                case 1 : song = "Let Her Go"; break;
                case 2 : song = "Beautiful Birds"; break;
                case 3 : song = "When We Were Young"; break;
                case 4 : song = "Home"; break;
                case 5 : song = "Everything"; break;
                case 6 : song = "If You Go"; break;
                default : song = "Let Her Go"; break;
            }
        }else if(artist.equals("Coldplay")){
            switch (id){
                case 1 : song = "The Scientist"; break;
                case 2 : song = "Fix You"; break;
                case 3 : song = "Hypnotised"; break;
                case 4 : song = "Magic"; break;
                case 5 : song = "A Sky Full Of Stars"; break;
                case 6 : song = "Clocks"; break;
                default : song = "The Scientist"; break;
            }
        }else if(artist.equals("4 Non Blondes")){
            switch (id){
                case 1 : song = "What’s up"; break;
                case 2 : song = "Superfly"; break;
                case 3 : song = "Spaceman"; break;
                case 4 : song = "In My Dreams"; break;
                case 5 : song = "Misty Mountain Hop"; break;
                case 6 : song = "Beautiful"; break;
                default : song = "What’s up"; break;
            }
        }

        return song;
    }

    public static String getTimeFromSong(String artist, int id) {
        String time = null;

        if(artist.equals("The Cranberries")){
            switch (id){
                case 1 : time = "4:23"; break;
                case 2 : time = "4:13"; break;
                case 3 : time = "4:33"; break;
                case 4 : time = "4:43"; break;
                case 5 : time = "4:53"; break;
                case 6 : time = "5:23"; break;
                default : time = "4:23"; break;
            }
        }else if(artist.equals("Lana Del Rey")){
            switch (id){
                case 1 : time = "5:23"; break;
                case 2 : time = "5:13"; break;
                case 3 : time = "5:33"; break;
                case 4 : time = "5:43"; break;
                case 5 : time = "5:53"; break;
                case 6 : time = "4:23"; break;
                default : time = "5:23"; break;
            }
        }else if(artist.equals("John Legend")){
            switch (id){
                case 1 : time = "3:23"; break;
                case 2 : time = "4:31"; break;
                case 3 : time = "5:56"; break;
                case 4 : time = "4:34"; break;
                case 5 : time = "5:02"; break;
                case 6 : time = "6:03"; break;
                default : time = "3:23"; break;
            }
        }else if(artist.equals("Passenger")){
            switch (id){
                case 1 : time = "5:15"; break;
                case 2 : time = "4:31"; break;
                case 3 : time = "5:56"; break;
                case 4 : time = "4:34"; break;
                case 5 : time = "5:02"; break;
                case 6 : time = "6:03"; break;
                default : time = "5:15"; break;
            }
        }else if(artist.equals("Coldplay")){
            switch (id){
                case 1 : time = "5:15"; break;
                case 2 : time = "4:31"; break;
                case 3 : time = "5:56"; break;
                case 4 : time = "4:34"; break;
                case 5 : time = "5:02"; break;
                case 6 : time = "6:03"; break;
                default : time = "5:15"; break;
            }
        }else if(artist.equals("4 Non Blondes")){
            switch (id){
                case 1 : time = "5:15"; break;
                case 2 : time = "4:31"; break;
                case 3 : time = "5:56"; break;
                case 4 : time = "4:34"; break;
                case 5 : time = "5:02"; break;
                case 6 : time = "6:03"; break;
                default : time = "5:15"; break;
            }
        }

        return time;
    }


    public static String getAlbumFromSong(String artist, int id) {
        String album = null;

        if(artist.equals("The Cranberries")){
            if(id == 1 || id == 2){
                album = "No Need to Argue";
            }else if(id == 3 || id == 4){
                album = "Bury the Hatchet";
            }else{
                album = "Everybody Else Is Doing It, So Why Can’t We?";
            }
        }else if(artist.equals("Lana Del Rey")){
            if(id == 1 || id == 2){
                album = "Honeymoon";
            }else if(id == 3 || id == 4){
                album = "Ultraviolence";
            }else{
                album = "Die for Me";
            }
        }else if(artist.equals("John Legend")){
            if(id == 1 || id == 2){
                album = "Darkness and Light";
            }else if(id == 3 || id == 4){
                album = "Love in the Future";
            }else{
                album = "wake up!";
            }
        }else if(artist.equals("Passenger")){
            if(id == 1 || id == 2){
                album = "Whispers";
            }else if(id == 3 || id == 4){
                album = "All the Little Lights";
            }else{
                album = "Divers and Submarines";
            }
        }else if(artist.equals("Coldplay")){
            if(id == 1 || id == 2){
                album = "Ghost Stories";
            }else if(id == 3 || id == 4){
                album = "Parachutes";
            }else{
                album = "A Head Full of Dreams";
            }
        }else if(artist.equals("4 Non Blondes")){
            if(id == 1 || id == 2){
                album = "Blast off";
            }else if(id == 3 || id == 4){
                album = "Bigger, Better, Faster, More!";
            }else{
                album = "Drifting";
            }
        }

        return album;
    }
}
