package maze;

import java.util.List;

public class Maze {
    private Integer width;
    private Integer height;
    private Boolean maze[][];
    private final Boolean notAccessibleWay[][];
    private Point start = new Point();
    private Point finish = new Point();

    /**
     * Коструктор поумолчанию задает ширину и высоту равными 10 и инициализирует лабиринт.
     */
    public Maze(){
        width = 10; 
        height = 10;
        maze = new Boolean[width][height];
        notAccessibleWay = new Boolean[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j] = false;
            }
        }
        finish.setX(width-1);
        finish.setY(height-1);
    }

    /**
    * Коструктор задает ширину и высоту, а также инициализирует лабиринт.
     * @param w ширина лабиринта.
     * @param h высота лабиринта.
    */
    public Maze(final Integer w, final Integer h){
        width = w;
        height = h;
        maze = new Boolean[width][height];
        notAccessibleWay = new Boolean[width][height];
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                maze[i][j] = false;
            }
        }
        finish.setX(width-1);
        finish.setY(height-1);
    }

    /**
     * Проверка на наличие стенки в лабиринте с координатами x и y.
     * @param p координаты ячейки.
     * @return false, если стенка, иначе true.
    */
    public Boolean isEmpty(final Point p){
        if (p.getX() >= width || p.getY() >= height || p.getX() <0 || p.getY() <0) return false;
        return !maze[p.getX()][p.getY()];
    }

    /**
     * Задание значения ячейке с заданными координатами.
     * @param p координаты ячейки.
     * @param isNotWall false, если стенка, иначе true.
     */
    public void set(final Point p, final Boolean isNotWall){
        maze[p.getX()][p.getY()] = isNotWall;	
    }

    /**
     * Определение возможности идти налево из заданной ячейки.
     * @param p координаты ячейки.
     * @return значение true, если маневр возможен, иначе false.
     */
    public Boolean left(final Point p){
        return isEmpty(Point.minus(p, new Point(1, 0)));
    }

    /**
     * Определение возможности идти направо из заданной ячейки.
     * @param p координаты ячейки.
     * @return значение true, если маневр возможен, иначе false.
     */
    public Boolean right(final Point p){
        return isEmpty(Point.plus(p, new Point(1, 0)));
    }

    /**
     * Определение возможности идти вверх из заданной ячейки.
     * @param p координаты ячейки.
     * @return значение true, если маневр возможен, иначе false.
     */
    public Boolean top(final Point p){
        return isEmpty(Point.plus(p, new Point(0, 1)));
    }

    /**
     * Определение возможности идти вниз из заданной ячейки.
     * @param p координаты ячейки.
     * @return значение true, если маневр возможен, иначе false.
     */
    public Boolean bottom(final Point p){
        return isEmpty(Point.minus(p, new Point(0, 1)));
    }

    /**
     * Изменение ширины лабиринта.
     * @param newWeight новая ширина лабиринта для задания.
     */
    public void setWeight(final Integer newWeight) {
        if (newWeight < 0) throw new ArrayIndexOutOfBoundsException("Некорректная ширина");
        
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
        else if(newWeight < width){
            if(finish.getX()>newWeight-1){
                boolean found = false;
                int w = newWeight-1, h = height-1;
                Point p = new Point();
                while(!found){
                    for(w = newWeight; w>0 && !found; ){
                        w--;
                        for(h = height; h>0 && !found; ){
                            h--;
                            p.setX(w);
                            p.setY(h);
                            if(!maze[w][h] && !start.equals(p)) found = true;
                        }

                    }
                }
                finish.setX(w);
                finish.setY(h);
            }
            if(start.getX() >newWeight-1){
                boolean found = false;
                int w = newWeight-1, h = height-1;
                Point p = new Point();
                while(!found){
                    for(w = newWeight; w>0 && !found; ){
                        w--;
                        for(h = height; h>0 && !found; ){
                            h--;
                            p.setX(w);
                            p.setY(h);
                            if(!maze[w][h]&& !finish.equals(p)) found = true;
                        }

                    }
                }
                start.setX(w);
                start.setY(h);
            }
        }

        width = newWeight;
    }

    /**
     * @return значение ширины лабиринта.
     */
    public Integer getWeight(){
        return width;
    }
    
    /**
     * @return значение высоты лабиринта.
     */
    public Integer getHight(){
        return height;
    }

    /**
     * Изменение высоты лабиринта.
     * @param newHight новая высота лабиринта для задания.
     */
    public void setHight(final Integer newHight) {
        if (newHight < 0) throw new ArrayIndexOutOfBoundsException("Некорректная высота");
        
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
        else if(newHight < height){
            if(finish.getY()>newHight-1){
                boolean found = false;
                int w = width-1, h = newHight-1;
                while(!found){
                    for(w = width; w>0 && !found; ){
                        w--;
                        for(h = newHight; h>0 && !found; ){
                            h--;
                            if(!maze[w][h]) found = true;
                        }

                    }
                }
                finish.setX(w);
                finish.setY(h);
            }
            if(start.getY() >newHight-1){
                boolean found = false;
                int w = width-1, h = newHight-1;
                Point p = new Point();
                while(!found){
                    for(w = width; w>0 && !found; ){
                        w--;
                        for(h = newHight; h>0 && !found; ){
                            h--;
                            p.setX(w);
                            p.setY(h);
                            if(!maze[w][h]&& !finish.equals(p)) found = true;
                        }

                    }
                }
                start.setX(w);
                start.setY(h);
            }
        }
        height = newHight;
    }

    /**
     * @return координаты стартовой точки по х.
     */
    public Integer getStartX(){
        return start.getX();
    }

    /**
     * @return координаты стартовой точки по у.
     */
    public Integer getStartY(){
        return start.getY();
    }
    
    /**
     * @return координаты стартовой точки.
     */
    public Point getStart() {
        return new Point(start);
    }
    
    /**
     * @param x координата точки по ширине для задания.
     */
    public void setStartX(final Integer x){
        if ((-1 < x) && (x < width)) start.setX(x);
        else throw new ArrayIndexOutOfBoundsException("Некорректная ширина");
    }
    
    /**
     * @param y координата по высоте.
     */
    public void setStartY(final Integer y){
        if ((-1 < y) & (y < height)) start.setY(y);
        else throw new ArrayIndexOutOfBoundsException("Некорректная высота");
    }
    
    /**
     * @param s координаты стартовой точки для задания.
     */
    public void setStart(final Point s) {
        if (s.getX() >= width || s.getX() < 0 || s.getY() >= height || s.getY() < 0)
            throw new ArrayIndexOutOfBoundsException("Некорректная координата стартовой точки");
        start = new Point(s);
    }
    
    /**
     * @return координаты финишной точки по ширине.
     */
    public Integer getFinishX(){
        return finish.getX();
    }

    /**
     * @return координаты финишной точки по высоте.
     */
    public Integer getFinishY(){
        return finish.getY();
    }
    
    /**
     * @return координаты финишной точки.
     */
    public Point getFinish() {
        return new Point(finish);
    }
    
    /**
     * @param x координаты финишной точки по ширине для задания.
     */
    public void setFinishX(final Integer x){
        if ((-1 < x) & (x < width)) finish.setX(x);
        else throw new ArrayIndexOutOfBoundsException("Некорректная координата финишной точки");
    }

    /**
     * @param y координаты финишной точки по высоте для задания.
     */
    public void setFinishY(final Integer y){
        if ((-1 < y) & (y < height)) finish.setY(y);
        else throw new ArrayIndexOutOfBoundsException("Некорректная координата финишной точки");
    }
    
    /**
     * @param f координаты финишной точки для задания.
     */
    public void setFinish(final Point f) {
        if (f.getX() >= width || f.getX() < 0 || f.getY() >= height || f.getY() < 0)
            throw new ArrayIndexOutOfBoundsException("Некорректная координата стартовой точки");
        finish = new Point(f);
    }
    

    /**
     * Поиск одного из наикратчайших путей по лабиринту из заданной точки.
     * @param p точка, с которой начинается поиск
     * @return список координат клеток одного из наикратчайший путей.
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
        
        
        Point pointLeft = Point.minus(point, new Point(1, 0));
        Point pointRight = Point.plus(point, new Point(1, 0));
        Point pointBot = Point.minus(point, new Point(0, 1));
        Point pointTop = Point.plus(point, new Point(0, 1));
        
        
        
        for(Point p : curr) {
            if (pointLeft != null && p.equals(pointLeft)) pointLeft = null;
            if (pointRight != null && p.equals(pointRight)) pointRight = null;
            if (pointTop != null && p.equals(pointTop)) pointTop = null;
            if (pointBot != null && p.equals(pointBot)) pointBot = null;
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
