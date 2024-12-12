import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class ArvoreRB {
    public static int countComps;

    enum Cor {
        VERMELHO, PRETO
    }

    static class Pokemon {
        private int id;
        private int gen;
        private String nome;
        private String descricao;
        private int raridade;
        private double peso;
        private double altura;
        private boolean isLegendary;
        private String data;
        private String types1;
        private String types2;
        private String habilidades;

        public Pokemon esq, dir, pai;
        public Cor cor;

        public Pokemon() {
            this.cor = Cor.VERMELHO; // Por padrão, novos nós são vermelhos
            this.esq = null;
            this.dir = null;
            this.pai = null;
        }

        // Getters e Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGen() {
            return gen;
        }

        public void setGen(int gen) {
            this.gen = gen;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public int getRaridade() {
            return raridade;
        }

        public void setRaridade(int raridade) {
            this.raridade = raridade;
        }

        public double getPeso() {
            return peso;
        }

        public void setPeso(double peso) {
            this.peso = peso;
        }

        public double getAltura() {
            return altura;
        }

        public void setAltura(double altura) {
            this.altura = altura;
        }

        public boolean isLegendary() {
            return isLegendary;
        }

        public void setLegendary(boolean isLegendary) {
            this.isLegendary = isLegendary;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getTypes1() {
            return types1;
        }

        public void setTypes1(String types1) {
            this.types1 = types1;
        }

        public String getTypes2() {
            return types2;
        }

        public void setTypes2(String types2) {
            this.types2 = types2;
        }

        public String getHabilidades() {
            return habilidades;
        }

        public void setHabilidades(String habilidades) {
            this.habilidades = habilidades;
        }
    }

    static class ArvoreRubroNegra {
        private Pokemon raiz;
        private final Pokemon nulo;

        ArvoreRubroNegra() {
            nulo = new Pokemon();
            nulo.cor = Cor.PRETO;
            raiz = nulo;
        }

        public void inserir(Pokemon novo) {
            Pokemon y = null;
            Pokemon x = raiz;

            while (x != nulo) {
                y = x;
                if (novo.getNome().compareTo(x.getNome()) < 0) {
                    x = x.esq;
                } else {
                    x = x.dir;
                }
            }

            novo.pai = y;

            if (y == null) {
                raiz = novo;
            } else if (novo.getNome().compareTo(y.getNome()) < 0) {
                y.esq = novo;
            } else {
                y.dir = novo;
            }

            novo.esq = nulo;
            novo.dir = nulo;
            novo.cor = Cor.VERMELHO;

            corrigirInsercao(novo);
        }

        private void corrigirInsercao(Pokemon z) {
            while (z.pai != null && z.pai.cor == Cor.VERMELHO) {
                if (z.pai == z.pai.pai.esq) {
                    Pokemon y = z.pai.pai.dir;
                    if (y.cor == Cor.VERMELHO) {
                        z.pai.cor = Cor.PRETO;
                        y.cor = Cor.PRETO;
                        z.pai.pai.cor = Cor.VERMELHO;
                        z = z.pai.pai;
                    } else {
                        if (z == z.pai.dir) {
                            z = z.pai;
                            rotacaoEsquerda(z);
                        }
                        z.pai.cor = Cor.PRETO;
                        z.pai.pai.cor = Cor.VERMELHO;
                        rotacaoDireita(z.pai.pai);
                    }
                } else {
                    Pokemon y = z.pai.pai.esq;
                    if (y.cor == Cor.VERMELHO) {
                        z.pai.cor = Cor.PRETO;
                        y.cor = Cor.PRETO;
                        z.pai.pai.cor = Cor.VERMELHO;
                        z = z.pai.pai;
                    } else {
                        if (z == z.pai.esq) {
                            z = z.pai;
                            rotacaoDireita(z);
                        }
                        z.pai.cor = Cor.PRETO;
                        z.pai.pai.cor = Cor.VERMELHO;
                        rotacaoEsquerda(z.pai.pai);
                    }
                }
            }
            raiz.cor = Cor.PRETO;
        }

        private void rotacaoEsquerda(Pokemon x) {
            Pokemon y = x.dir;
            x.dir = y.esq;
            if (y.esq != nulo) {
                y.esq.pai = x;
            }
            y.pai = x.pai;
            if (x.pai == null) {
                raiz = y;
            } else if (x == x.pai.esq) {
                x.pai.esq = y;
            } else {
                x.pai.dir = y;
            }
            y.esq = x;
            x.pai = y;
        }

        private void rotacaoDireita(Pokemon y) {
            Pokemon x = y.esq;
            y.esq = x.dir;
            if (x.dir != nulo) {
                x.dir.pai = y;
            }
            x.pai = y.pai;
            if (y.pai == null) {
                raiz = x;
            } else if (y == y.pai.dir) {
                y.pai.dir = x;
            } else {
                y.pai.esq = x;
            }
            x.dir = y;
            y.pai = x;
        }

        public void buscar(String nome) {
            System.out.print("raiz ");
            buscar(raiz, nome);
        }

        private void buscar(Pokemon i, String nome) {
            countComps++;
            if (i == null || i == nulo) {
                System.out.println("NAO");
                return;
            } else if (i.getNome().equals(nome)) {
                System.out.println("SIM");
                return;
            } else {
                if (i.getNome().compareTo(nome) > 0) {
                    System.out.print("esq ");
                    buscar(i.esq, nome);
                } else {
                    System.out.print("dir ");
                    buscar(i.dir, nome);
                }
            }
        }

        public void mostrar() {
            mostrar(raiz);
        }

        private void mostrar(Pokemon no) {
            if (no != nulo) {
                mostrar(no.esq);
                System.out.println(no.getNome() + " [" + no.cor + "]");
                mostrar(no.dir);
            }
        }
    }

    public static Pokemon learquivo(String caminho, int numero, Pokemon generico) {
        File pokedex = new File(caminho);
        Scanner sc = null;

        try {
            sc = new Scanner(pokedex);

            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] partes = line.split(",");
                generico.setNome(partes[2]);
                if (Integer.parseInt(partes[0]) == numero) {
                    sc.close();
                    return generico;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        return generico;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String numero = sc.nextLine();

        Pokemon poke;
        ArvoreRubroNegra a = new ArvoreRubroNegra();

        String caminho = "/tmp/pokemon.csv";

        while (!numero.equals("FIM")) {
            poke = learquivo(caminho, Integer.parseInt(numero), new Pokemon());
            a.inserir(poke);
            numero = sc.nextLine();
        }

        numero = sc.nextLine();
        while (!numero.equals("FIM")) {
            a.buscar(numero);
            numero = sc.nextLine();
        }

        sc.close();
    }
}
