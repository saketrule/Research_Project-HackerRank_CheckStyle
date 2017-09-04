package org.nikolavp.algorithm.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TODO: Incomplete
 * https://www.hackerrank.com/challenges/fighting-pits
 * @author Nikola Petrov nikola.petrov@ontotext.com
 */
public class FightingPits {

 static class Team {
  private long total = 0;
  List<Integer> members = new ArrayList<>();

  public void addMember(int strength) {
   members.add(strength);
   total += strength;
  }
 }

 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);

  final int n = scanner.nextInt();
  final int k = scanner.nextInt();
  final int q = scanner.nextInt();

  Team[] teams = new Team[k];
  for (int i = 0; i < n; i++) {
   final int strength = scanner.nextInt();
   final int team = scanner.nextInt();
   final int idx = team - 1;
   if (teams[idx] == null) {
    teams[idx] = new Team();
   }
   teams[idx].addMember(strength);
  }


  for (int i = 0; i < q; i++) {
   final int qType = scanner.nextInt();
   switch (qType) {
   case 1: {
    final int strength = scanner.nextInt();
    
   }
   }
  }
 }
}