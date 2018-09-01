package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.*;

public class Solver {
    ArrayDeque<WorldState> list;
    MinPQ<searchNode> fringe;
    Mycomparator cmptr;
    int move;

    public class searchNode{
        WorldState state;
        int moves;
        searchNode parentNode;
        int priority;
        public searchNode(WorldState currentState, int moves, searchNode parentNode){
            this.state = currentState;
            this.moves = moves;
            this.parentNode = parentNode;
            priority = currentState.estimatedDistanceToGoal() + moves;
        }

    }

    private class Mycomparator implements Comparator<searchNode>{
        @Override
        public int compare(searchNode s1, searchNode s2){
            return s1.priority - s2.priority;
        }
    }


    public Solver(WorldState initial){
        list = new ArrayDeque<>();
        cmptr = new Mycomparator();
        searchNode dommy = new searchNode(initial,0,null);
        searchNode current = new searchNode(initial,0,dommy);
        fringe = new MinPQ(cmptr);
        fringe.insert(current);

        while(!fringe.isEmpty() && !current.state.isGoal()){
            current = fringe.delMin();
            for(WorldState w : current.state.neighbors()){
                if(!w.equals(current.parentNode.state) && !w.equals(current.state)){
                    searchNode s = new searchNode(w, current.moves + 1, current);
                    fringe.insert(s);
                }
            }
        }
        move = current.moves;
        while(current != null){
            list.addFirst(current.state);
            current = current.parentNode;
        }
        list.removeFirst();


    }
    public int moves(){
        return move;
    }

    public Iterable<WorldState> solution(){
        return list;
    }



}
