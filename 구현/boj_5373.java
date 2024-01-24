package 구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj_5373 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            Cube cube = new Cube();
            for (int j = 0; j < n; j++) {
                cube.roll(st.nextToken());
            }
            bw.write(cube.toString());
        }

        bw.close();
    }

    public static class Cube {
        char[][] up;
        char[][] down;
        char[][] front;
        char[][] back;
        char[][] left;
        char[][] right;

        public Cube() {
            this.up = new char[][] {
                {'w', 'w', 'w'},
                {'w', 'w', 'w'},
                {'w', 'w', 'w'}
            };
            this.down = new char[][] {
                {'y', 'y', 'y'},
                {'y', 'y', 'y'},
                {'y', 'y', 'y'}
            };
            this.front = new char[][] {
                {'r', 'r', 'r'},
                {'r', 'r', 'r'},
                {'r', 'r', 'r'}
            };
            this.back = new char[][] {
                {'o', 'o', 'o'},
                {'o', 'o', 'o'},
                {'o', 'o', 'o'}
            };
            this.left = new char[][] {
                {'g', 'g', 'g'},
                {'g', 'g', 'g'},
                {'g', 'g', 'g'}
            };
            this.right = new char[][] {
                {'b', 'b', 'b'},
                {'b', 'b', 'b'},
                {'b', 'b', 'b'}
            };
        }   

        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(this.up[i][j]);
                }
                sb.append('\n');
            }

            return sb.toString();
        }

        public void roll(String command) {
            switch (command) {
                case ("U+"): this.rollUp(1); break;
                case ("U-"): this.rollUp(3); break;
                case ("D+"): this.rollDown(1); break;
                case ("D-"): this.rollDown(3); break;
                case ("F+"): this.rollFront(1); break;
                case ("F-"): this.rollFront(3); break;
                case ("B+"): this.rollBack(1); break;
                case ("B-"): this.rollBack(3); break;
                case ("L+"): this.rollLeft(1); break;
                case ("L-"): this.rollLeft(3); break;
                case ("R+"): this.rollRight(1); break;
                case ("R-"): this.rollRight(3); break;
            }
        }

        private void rollUp(int times) {
            for (int i = 0; i < times; i++) {
                char[] tmp = this.front[0].clone();
                for (int j = 0; j < 3; j++) {
                    this.front[0][j] = this.right[0][j];
                    this.right[0][j] = this.back[0][j];
                    this.back[0][j] = this.left[0][j];
                    this.left[0][j] = tmp[j];
                }

                // 1 2 3    7 4 1
                // 4 5 6 -> 8 5 2
                // 7 8 9    9 6 3

                char[][] upTmp = new char[3][3];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        upTmp[c][2 - r] = this.up[r][c];
                    }
                }
                
                this.up = upTmp;
            }
        }
        
        private void rollDown(int times) {
            for (int i = 0; i < times; i++) {
                char[] tmp = this.front[2].clone();
                for (int j = 0; j < 3; j++) {
                    this.front[2][j] = this.left[2][j];
                    this.left[2][j] = this.back[2][j];
                    this.back[2][j] = this.right[2][j];
                    this.right[2][j] = tmp[j];
                }

                char[][] downTmp = new char[3][3];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        downTmp[c][2 - r] = this.down[r][c];
                    }
                }

                this.down = downTmp;
            }
        }
        
        private void rollFront(int times) {
            for (int i = 0; i < times; i++) {
                char[] tmp = this.up[2].clone();
                for (int j = 0; j < 3; j++) {
                    this.up[2][j] = this.left[2-j][2];
                    this.left[2-j][2] = this.down[0][2-j];
                    this.down[0][2-j] = this.right[j][0];
                    this.right[j][0] = tmp[j];
                }

                char[][] frontTmp = new char[3][3];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        frontTmp[c][2 - r] = this.front[r][c];
                    }
                }

                this.front = frontTmp;
            }
        }
        
        private void rollBack(int times) {
            for (int i = 0; i < times; i++) {
                char[] tmp = this.up[0].clone();
                for (int j = 0; j < 3; j++) {
                    this.up[0][j] = this.right[j][2];
                    this.right[j][2] = this.down[2][2 - j];
                    this.down[2][2 - j] = this.left[2-j][0];
                    this.left[2-j][0] = tmp[j];
                }

                char[][] backTmp = new char[3][3];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        backTmp[c][2 - r] = this.back[r][c];
                    }
                }

                this.back = backTmp;
            }
        }
        
        private void rollLeft(int times) {
            // 앞 -> 아래 -> 뒤 -> 위
            for (int i = 0; i < times; i++) {
                char[] tmp = new char[] {this.up[0][0], this.up[1][0], this.up[2][0]};
                for (int j = 0; j < 3; j++) {
                    this.up[j][0] = this.back[2-j][2];
                    this.back[2-j][2] = this.down[j][0];
                    this.down[j][0] = this.front[j][0];
                    this.front[j][0] = tmp[j];
                }

                char[][] leftTmp = new char[3][3];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        leftTmp[c][2 - r] = this.left[r][c];
                    }
                }

                this.left = leftTmp;
            }
        }
        
        private void rollRight(int times) {
            for (int i = 0; i < times; i++) {
                char[] tmp = new char[] {this.front[0][2], this.front[1][2], this.front[2][2]};
                for (int j = 0; j < 3; j++) {
                    this.front[j][2] = this.down[j][2];
                    this.down[j][2] = this.back[2-j][0];
                    this.back[2-j][0] = this.up[j][2];
                    this.up[j][2] = tmp[j];
                }
                
                char[][] rightTmp = new char[3][3];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        rightTmp[c][2 - r] = this.right[r][c];
                    }
                }
                
                this.right = rightTmp;
            }
        }
    }
}
