
package GAME;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import CArds.Cards;
import CArds.Minion;
import Grapic.MainFrame;
import Heroooo.*;
public class Main {

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});



//CLI s=new CLI();

	}

}
