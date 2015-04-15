package br.uece.lotus.tools;

import br.uece.lotus.Component;
import br.uece.lotus.State;
import br.uece.lotus.Transition;
import br.uece.lotus.viewer.TransitionViewFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucas on 04/03/15.
 */
public class TraceParser {
    private final Map<String, State> mStates = new HashMap<>();
    State mCurrentState;
    private Component mComponent;
    private int id = 0;
    private String namesDestinysState;
    private ArrayList<String> labals;

    protected Component parseFile(InputStream input) {
        mComponent = new Component();
        State initialState = mComponent.newState(0);
        mStates.put(initialState.getLabel(), initialState);
        if (input != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] trace = line.split(",");
                    System.out.println("trace.length:" + trace.length);

                    mCurrentState = initialState;

                    for (int i = 0; i < trace.length; i++) {
                        if (trace[i] != null) {
                            System.out.println("vetor: " + trace[i]);
                            Transition trans = verificaionExistenceTransition(trace[i].trim(), mCurrentState);
                            if (trans != null) {
                                mCurrentState = travel(trans);
                            } else {
//                                System.out.println("i+1:"+(i+1));
//                                System.out.println(" trace.length"+ trace.length);
                                if (i + 1 < trace.length) {
                                    Transition nextTransition = verificaionExistenceTransition(trace[i + 1].trim());
                                    if (nextTransition != null) {
                                        System.out.println("proxima transição existe");
                                        if (verificationCaseComma(nextTransition, i, trace)) {
                                            System.out.println("caso virgula");
                                            addCommar(mCurrentState, trace[i].trim(), nextTransition.getSource());
                                            break;
                                        }

                                    }
                                }
                                namesDestinysState = String.valueOf(mComponent.getStatesCount());
                                System.out.println(mCurrentState.getLabel() + " " + trace[i] + " " + namesDestinysState);
                                adicionarTransicao(mCurrentState.getLabel(), trace[i].trim(), namesDestinysState);
                            }


                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        new LTSALayouter().layout(mComponent);
        mComponent.setName("Untitled");
        return mComponent;
    }

    private Transition verificaionExistenceTransition(String nameTrans, State currentState) {
        for (Transition t : currentState.getOutgoingTransitionsList()) {
            if (t.getLabel().equals(nameTrans)) {
                return t;
            }

        }
        return null;

    }

    private Transition verificaionExistenceTransition(String nameTransistion) {
        for (Transition t : mComponent.getTransitions()) {
            if (t.getLabel().equals(nameTransistion)) {
                return t;
            }
        }
        return null;
    }

    private State travel(Transition trans) {

        return trans.getDestiny();
    }

    private void addCommar(State estadoOrigem, String acao, State estadoDestino) {
        Transition tras = pegandoTransicaoPorStadoOrigEStadoDest(estadoOrigem, estadoDestino);//possivel problema, outra transiçao para o mesmo estado de ori e estado de dest
        if (tras == null) {
            return;
        }
        System.out.println("tras" + tras.getLabel());
        mCurrentState = estadoDestino;
        mComponent.buildTransition(estadoOrigem, estadoDestino)
                .setLabel(acao);
        tras.setLabel(tras.getLabel() + "," + acao);


    }

    private boolean verificationCaseComma(Transition transition, int posicaoDoTrace, String[] linhaDoTrace) {
        boolean aux = false;
//        if (!transition.getLabel().equals(linhaDoTrace[posicaoDoTrace+1])) {
//            return false;
//        }
        State current = transition.getSource();
        System.out.println("current:" + current.getLabel());

        for (int x = posicaoDoTrace + 1; x < linhaDoTrace.length; x++) {
            for (Transition t : current.getOutgoingTransitionsList()) {
                System.out.println("transição:" + t.getLabel());
                System.out.println("linhaDoTrace[x]:" + linhaDoTrace[x]);
                if (t.getLabel().equals(linhaDoTrace[x].trim())) {
                    aux = true;
                    current = t.getDestiny();
                    break;
                }
            }
        }
System.out.println("retorno do commme"+aux);
        return aux;

    }


    private Transition pegandoTransicaoPorStadoOrigEStadoDest(State orig, State dest) {
        for (Transition t : mComponent.getTransitions()) {
            if (t.getSource().getLabel().equals(orig.getLabel()) && t.getDestiny().getLabel().equals(dest.getLabel())) {
                return t;
            }
        }
        return null;
    }

    private void adicionarTransicao(String estadoOrigem, String acao, String estadoDestino) {
        State src = getOrCreateState(estadoOrigem);
        State dst = getOrCreateState(estadoDestino);
        mCurrentState = dst;
        mComponent.buildTransition(src, dst)
                .setLabel(acao)
                .setViewType(TransitionViewFactory.Type.LINEAR)
                .create();

    }

    private void alias(String estadoOrigem, String nome) {
        State s = getOrCreateState(estadoOrigem);
        mStates.put(nome, s);
        mComponent.setName(estadoOrigem);
    }

    private State getOrCreateState(String estadoOrigem) {
        State s = mStates.get(estadoOrigem);
        if (s == null) {
            s = mComponent.newState(mComponent.getStatesCount());
            if (mComponent.getStatesCount() == 0) {
                s.setAsInitial();
            }
            mStates.put(estadoOrigem, s);
        }
        return s;
    }

}


