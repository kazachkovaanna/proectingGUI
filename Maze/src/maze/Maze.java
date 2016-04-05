package maze;

import java.util.List;

public class Maze {
    private Integer weight;
    private Integer hight;
    private Boolean maze[][];
    private Integer startX, startY;
    private Integer finishX, finishY;

    public class Point {
        private Integer X;
        private Integer Y;

        /**
         *Конструктор инициализирует все поля класса значениями 0
         */
        public Point() {
            X = 0;
            Y = 0;
        }

        /**
         * Коструктор инициализирует значение полей заданными параметрами
         * @param x - задает значение поля X
         * @param y - задеат значение поля Y
         */
        public Point(final Integer x, final Integer y) {
            X = x;
            Y = y;
        }

        /**
         * Конструктор копирования инициализирует значения полей из копии
         * @param ob - оригинал объекта для копирования
         */
        public Point(final Point ob){
            X = ob.X;
            Y = ob.Y;
        }

        /**
         * Задает параметр X
         * @param x - координата X
         */
        public void setX(final Integer x) {
            X = x;
        }

        /**
         * Задает параметр Y
         * @param y - координата Y
         */
        public void setY(final Integer y) {
            Y = y;
        }

        /**
         * Возвращает координату X
         * @return значение координаты X
         */
        public Integer getX() {
            return X;
        }

        /**
         * Возвращает координату Y
         * @return значение координаты Y
         */
        public Integer getY() {
            return Y;
        }
    }

    /**
     * Коструктор поумолчанию задает ширину и высоту равными 10 и инициализирует лабиринт
     */
    public Maze(){
        weight = 10; 
        hight = 10;
        maze = new Boolean[weight][hight];
        for(int i = 0; i < weight; i++){
            for(int j = 0; j < hight; j++){
                maze[i][j] = false;
            }
        }
    }

    /**
    * Коструктор задает ширину и высоту, а также инициализирует лабиринт
     * @param w - ширина лабиринта
     * @param h - высота лабиринта
    */
    public Maze(Integer w, Integer h){
        weight = w;
        hight = h;
        maze = new Boolean[weight][hight];
        for(int i=0; i<weight; i++){
            for(int j=0; j<hight; j++){
                maze[i][j] = false;
            }
        }
    }

    /**
     * Проверка на наличие стенки в лабиринте с координатами x и y
     * @param x - значение клетки по ширине
     * @param y - значение клетки по высоте
     * @return false, если стенка, иначе true
    */
    public Boolean isEmpty(Integer x, Integer y){
        return !maze[x][y];
    }

    /**
     * Задание значения ячейке с заданными координатами
     * @param x - координата по ширине
     * @param y - координата по высоте
     * @param maz - false, если стенка, иначе true
     */
    public void set(Integer x, Integer y, Boolean maz){
        maze[x][y] = maz;	
    }

    /**
     * Определение возможности идти налево из заданной ячейки
     * @param x - значение по ширине
     * @param y - значение по высоте
     * @return значение true, если маневр возможен, иначе false
     */
    public Boolean left(Integer x, Integer y){
        if (x - 1 < 0) return false;
        return isEmpty(x - 1, y);
    }

    /**
     * Определение возможности идти направо из заданной ячейки
     * @param x - значение по ширине
     * @param y - значение по высоте
     * @return значение true, если маневр возможен, иначе false
     */
    public Boolean right(Integer x, Integer y){
        if (x + 1 > weight) return false;
        return isEmpty(x + 1, y);
    }

    /**
     * Определение возможности идти вверх из заданной ячейки
     * @param x - значение по ширине
     * @param y - значение по высоте
     * @return значение true, если маневр возможен, иначе false
     */
    public Boolean top(Integer x, Integer y){
        if (y + 1 > hight) return false;
        return isEmpty(x, y + 1);
    }

    /**
     * Определение возможности идти вниз из заданной ячейки
     * @param x - значение по ширине
     * @param y - значение по высоте
     * @return значение true, если маневр возможен, иначе false
     */
    public Boolean bottom(Integer x, Integer y){
        if (y - 1 < 0) return false;
        return isEmpty(x, y - 1);
    }

    /**
     * Изменение ширины лабиринта
     * @param newWeight - новая ширина лабиринта
     */
    public void setWeight(Integer newWeight){
        if (newWeight < 0) {
            System.out.println("Невозможно изменить ширину!");
            return;
        }
        
        Boolean[][] exmaze  = maze;

        maze = new Boolean[newWeight][hight]; 

        for (int i=0; i < Integer.min(newWeight, weight); i++){ 
            System.arraycopy(exmaze[i], 0, maze[i], 0, hight);
        }

        if (newWeight > weight) {
            for (int i = weight; i < newWeight; i++){ 
                for(int j = 0; j < hight; j++){
                        maze[i][j] = false;
                }
            }
        }

        weight = newWeight; 

    }

    /**
     * Возврат текущей ширины лыбиринта
     * @return значение ширины лабиринта
     */
    public Integer getWeight(){
        return weight;
    }

    /**
     * Изменение высота лабиринта 
     * @param newHight - новая высота лабиринта
     */
    public void setHight(Integer newHight){
        if (newHight < 0) {
            System.out.println("Невозможно изменить ширину!");
            return;
        }
        
        Boolean[][] exmaze = maze;
        maze = new Boolean[weight][newHight]; 

        for (int i=0; i < Integer.min(newHight, hight); i++){ 
            for(int j = 0; j < weight; j++){
                    maze[j][i] = exmaze[j][i];
            }
        }

        if (newHight > hight) {
            for (int i = hight; i < newHight; i++){ //
                for(int j = 0; j < weight; j++){
                    maze[j][i] = false;
                }
            }
        }
        hight = newHight;
    }

    /**
     * Возврат высоту лабиринта
     * @return - высот алабиринта
     */
    public Integer getHight(){
        return hight;
    }

    /**
     * Задание координаты стартовой точки по x
     * @param x - координата точки по ширине
     */
    public void setStartX(final Integer x){
        if ((-1 < x) & (x < weight)) startX = x;
        else System.out.println("Выход за пределы лабиринта");
    }

    /**
     * Задание координаты стартовой точки по y
     * @param y - координата по высоте
     */
    public void setStartY(final Integer y){
        if ((-1 < y) & (y < hight)) startY = y;
        else System.out.println("Выход за пределы лабиринта");
    }

    /**координаты начала, их задание
     * 
     * @return 
     */
    public Integer getStartX(){
        return startX;
    }

    public Integer getStartY(){
        return startY;
    }

    /**координаты конца, их задание
     * 
     */
    public void setFinishX(Integer x){
        if ((-1 < x) & (x < weight)) finishX = x;
        else System.out.println("Выход за пределы лабиринта");
    }

    public void setFinishY(Integer y){
        if ((-1 < y) & (y < hight)) finishY = y;
        else System.out.println("Выход за пределы лабиринта");
    }

    public Integer getFinishX(){
        return finishX;
    }

    public Integer getFinishY(){
        return finishY;
    }

    /**
     *
     * @param p точка, с которой начинается поиск
     * @return список координат клеток одного из наикратчайший путей, 
     * null если пути не существует или запрос делается из конечной точки.
     */
    public List<Point> getPath(Point p) {
        Integer length = Integer.MAX_VALUE;                 //длина наикратчайшего пути
        
        return null;
    }
    
    private List<Point> getPath(Point p, Integer cLength, Integer mLength) {
        return null;
    }
}
