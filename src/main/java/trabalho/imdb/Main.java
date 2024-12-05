package trabalho.imdb;

import java.sql.SQLException;
import java.util.Scanner;

import trabalho.imdb.exception.InsertException;
import trabalho.imdb.exception.SelectException;
import trabalho.imdb.negocio.Sistema;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Sistema sistema;
        try {
            sistema = new Sistema("0916");
            int opcao = -1;
            while (opcao != 0) {
                imprimirMenu();
                opcao = Integer.parseInt(scan.nextLine());

                switch (opcao) {
                    case 0:
                        System.out.print("Ate mais\n");
                        break;
                    case 1:
                        System.out.println("\n---Cadastro de Filme---");
                        System.out.print("Digite o nome do filme a ser buscado: ");
                        String nome = scan.nextLine();
                        System.out.println(sistema.listarFilmes(nome));
                        for (int i = 0; i < 1; i++) {
                            System.out.print("Digite o id do filme escolhido: ");
                            String id = scan.nextLine();
                            System.out.print("Digite o nome exato do filme escolhido: ");
                            nome = scan.nextLine();
                            System.out.println();
                            try {
                                sistema.cadastrarFilme(id, nome);
                            } catch (InsertException er) {
                                System.err.println(er.getMessage());
                            } catch (SelectException er) {
                                System.err.println(er.getMessage());
                            }
                        }
                        break;
                    case 2:
                        System.out.println("\n---Cadastro de Ator---");
                        System.out.print("Digite o nome do ator: ");
                        String name = scan.nextLine();
                        try {
                            sistema.cadastrarAtor(name);
                        } catch (InsertException er) {
                            System.err.println(er.getMessage());
                        } catch (SelectException er) {
                            System.err.println(er.getMessage());
                        }
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("\n---Mostrar Filmes---");
                        try {
                            System.out.print(sistema.mostrarFilmes());
                        } catch (SelectException er) {
                            System.err.println(er);
                            System.err.println(er.getMessage());
                        }
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("\n---Mostrar Atores---");
                        try {
                            System.out.print(sistema.mostrarAtores());
                        } catch (SelectException er) {
                            System.err.println(er.getMessage());
                        }
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("\n---Mostrar Filme---");
                        String id;
                        System.out.print("Digite o id do filme a ser mostrado: ");
                        id = scan.nextLine();
                        System.out.print(sistema.mostrarFilme(id));
                        System.out.println();
                        break;
                    case 6:
                        System.out.println("\n---Mostrar Ator---");
                        String idi;
                        System.out.print("Digite o id do ator a ser mostrado: ");
                        idi = scan.nextLine();
                        System.out.print(sistema.mostrarAtor(idi));
                        break;
                    default:
                        System.out.print("Opcao invalida");
                        break;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Erro no carregamento do Driver JDBC.");
        } catch (SQLException e) {
            System.err.println("Erro na conexao");
        } catch (SelectException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void imprimirMenu() {
        System.out.println(
                "\n(0) Sair do programa\n(1) Cadastrar Filme\n(2) Cadastrar Ator\n(3) Mostrar Filmes\n(4) Mostrar Atores\n(5) Mostrar Filme\n(6) Mostrar Ator");
        System.out.print("Escolha uma das opcoes acima: ");
    }
}