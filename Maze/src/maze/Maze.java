package maze;

import java.util.List;

public class Maze {
    private Integer width;
    private Integer height;
    private Boolean maze[][];
    private Point start = new Point();
    private Point finish = new Point();

    /**
     * Коструктор поумолчанию задает ширину и высоту равными 10 и инициализирует лабиринт
     */
    public Maze(){
        width = 10; 
        height = 10;
        maze = new Boolean[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j] = false;
            }
        }
    }

    /**
    * Коструктор задает ширину и высоту, а также инициализирует лабиринт
     * @param w ширина лабиринта
     * @param h высота лабиринта
    */
    public Maze(final Integer w, final Integer h){
        width = w;
        height = h;
        maze = new Boolean[width][height];
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                maze[i][j] = false;
            }
        }
    }

    /**
     * Проверка на наличие стенки в лабиринте с координатами x и y
     * @param p координаты ячейки
     * @return false, если стенка, иначе true
    */
    public Boolean isEmpty(final Point p){
        return !maze[p.getX()][p.getY()];
    }

    /**
     * Задание значения ячейке с заданными координатами
     * @param p координаты ячейки
     * @param maz false, если стенка, иначе true
     */
    public void set(final Point p, final Boolean maz){
        maze[p.getX()][p.getY()] = maz;	
    }

    /**
     * Определение возможности идти налево из заданной ячейки
     * @param p координаты ячейки
     * @return значение true, если маневр возможен, иначе false
     */
    public Boolean left(final Point p){
        if (p.getX() - 1 < 0) return false;
        return isEmpty(p);
    }

    /**
     * Определение возможности идти направо из заданной ячейки
     * @param p координаты ячейки
     * @return значение true, если маневр возможен, иначе false
     */
    public Boolean right(final Point p){
        if (p.getX() + 1 > width) return false;
        return isEmpty(p);
    }

    /**
     * Определение возможности идти вверх из заданной ячейки
     * @param p координаты ячейки
     * @return значение true, если маневр возможен, иначе false
     */
    public Boolean top(final Point p){
        if (p.getY() + 1 > height) return false;
        return isEmpty(p);
    }

    /**
     * Определение возможности идти вниз из заданной ячейки
     * @param p координаты ячейки
     * @return значение true, если маневр возможен, иначе false
     */
    public Boolean bottom(final Point p){
        if (p.getY() - 1 < 0) return false;
        return isEmpty(p);
    }

    /**
     * Изменение ширины лабиринта
     * @param newWeight новая ширина лабиринта
     */
    public void setWeight(final Integer newWeight){
        if (newWeight < 0) {
            System.out.println("Невозможно изменить ширину!");
            return;
        }
        
        Boolean[][] exmaze  = maze;

        maze = new Boolean[newWeight][height]; 

        for (int i=0; i < Integer.min(newWeight, width); i++){ 
            System.arraycopy(exmaze[i], 0, maze[i], 0, height);
        }

        if (newWeight > width) {
            for (int i = width; i < newWeight; i++){ 
                for(int j = 0; j < height; j++){
                        maze[i][j] = false;
                }
            }
        }

        width = newWeight; 

    }

    /**
     * Возврат текущей ширины лыбиринта
     * @return значение ширины лабиринта
     */
    public Integer getWeight(){
        return width;
    }

    /**
     * Изменение высота лабиринта 
     * @param newHight новая высота лабиринта
     */
    public void setHight(final Integer newHight){
        if (newHight < 0) {
            System.out.println("Невозможно изменить ширину!");
            return;
        }
        
        Boolean[][] exmaze = maze;
        maze = new Boolean[width][newHight]; 

        for (int i=0; i < Integer.min(newHight, height); i++){ 
            for(int j = 0; j < width; j++){
                    maze[j][i] = exmaze[j][i];
            }
        }

        if (newHight > height) {
            for (int i = height; i < newHight; i++){ //
                for(int j = 0; j < width; j++){
                    maze[j][i] = false;
                }
            }
        }
        height = newHight;
    }

    /**
     * Возврат высоту лабиринта
     * @return высоты алабиринта
     */
    public Integer getHight(){
        return height;
    }

    /**
     * Задание координаты стартовой точки по x
     * @param x координата точки по ширине
     */
    public void setStartX(final Integer x){
        if ((-1 < x) & (x < width)) start.setX(x);
        else System.out.println("Выход за пределы лабиринта");
    }

    /**
     * Задание координаты стартовой точки по y
     * @param y координата по высоте
     */
    public void setStartY(final Integer y){
        if ((-1 < y) & (y < height)) start.setY(y);
        else System.out.println("Выход за пределы лабиринта");
    }

    /**координаты начала, их задание
     * 
     * @return 
     */
    public Integer getStartX(){
        return start.getX();
    }

    public Integer getStartY(){
        return start.getY();
    }
    
    public void setStart(final Point Start) {
        //проверка
        start = new Point(Start);
    }
    
    public Point getStart() {
        return start;
    }

    /**координаты конца, их задание
     * 
     */
    public void setFinishX(final Integer x){
        if ((-1 < x) & (x < width)) finish.setX(x);
        else System.out.println("Выход за пределы лабиринта");
    }

    public void setFinishY(final Integer y){
        if ((-1 < y) & (y < height)) finish.setY(y);
        else System.out.println("Выход за пределы лабиринта");
    }
    
    public void setFinish(final Point f) {
        //проверка
        finish = new Point(f);
    }
    
    public Point getFinish() {
        return finish;
    }

    public Integer getFinishX(){
        return finish.getX();
    }

    public Integer getFinishY(){
        return finish.getY();
    }

    /**
     *
     * @param p точка, с которой начинается поиск
     * @return список координат клеток одного из наикратчайший путей, 
     * null если пути не существует или запрос делается из конечной точки.
     */
    public List<Point> getPath(final Point p) {
        List<Point> curr = new java.util.ArrayList<>();
        List<Point> best = new java.util.ArrayList<>();
        curr.add(p);
        getPath(curr, best);
        return best;
    }
    
    private void getPath(List<Point> curr, List<Point> best) {
        int currSize = curr.size();
        int bestSize = best.size();
        if (bestSize != 0 && currSize >= bestSize) return;
        
        Point point = curr.get(currSize - 1);
        if (point.equals(getFinish())) {
            best.clear();
            best.addAll(curr);
            return;
        }
        
        Point pointLeft = new Point(point.getX() - 1, point.getY());
        Point pointRight = new Point(point.getX() + 1, point.getY());
        Point pointBot = new Point(point.getX(), point.getY() - 1);
        Point pointTop = new Point(point.getX(), point.getY() + 1);
        
        for(Point p : curr) {
            if (p.equals(pointLeft)) pointLeft = null;
            if (p.equals(pointRight)) pointRight = null;
            if (p.equals(pointTop)) pointTop = null;
            if (p.equals(pointBot)) pointBot = null;
        }
        
        if (null != pointLeft && left(point)) {
            curr.add(pointLeft);
            getPath(curr, best);
            curr.remove(currSize);
        }
        
        if (null != pointTop && top(point)) {
            curr.add(pointTop);
            getPath(curr, best);
            curr.remove(currSize);
        }
        
        if (null != pointRight && right(point)) {
            curr.add(pointRight);
            getPath(curr, best);
            curr.remove(currSize);
        }
        
        if (null != pointBot && bottom(point)) {
            curr.add(pointBot);
            getPath(curr, best);
            curr.remove(currSize);
        }
    }
}
