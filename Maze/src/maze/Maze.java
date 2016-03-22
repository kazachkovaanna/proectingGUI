package maze;
public class Maze {
	
	public int weight;
	public int hight;
	boolean maze[][];
	int startX, startY;
	int finishX, finishY;
	
	/**конструктор
	*
	*/
	public Maze(){
		weight = hight = 10;
		maze = new boolean[weight][hight];
		for(int i=0; i<weight; i++){
			for(int j=0; j<hight; j++){
				maze[i][j] = false;
			}
		}
	}
	
	/**конструктор с заданием ширины и высоты
	*
	*/
	public Maze(int w, int h){
		weight = w;
		hight = h;
		maze = new boolean[weight][hight];
		for(int i=0; i<weight; i++){
			for(int j=0; j<hight; j++){
				maze[i][j] = false;
			}
		}
	}
	
	/**возвращает true если ячейка пустая
	*
	*/
	public boolean isEmpty(int x, int y){
		if (maze[x][y] == false) return true;
		else return false;
	}
	
	/**задает значение ячейке
	 * 
	 */
	public void set(int x, int y, boolean maz){
		maze[x][y] = maz;	
	}
	
	/**возвращает значение ячейки
	 * 
	 */
	public boolean get(int x, int y){
		return maze[x][y];
	}
	/**возвращает true если идти налево можно (слева пусто)
	 * 
	 */
	public boolean left(int x, int y){
		if (x-1 < 0) return false;
		if ( !isEmpty(x-1, y) ) return false;
		else return true;
	}
	
	/**возвращает true если идти направо можно (справа пусто)
	 * 
	 */
	public boolean right(int x, int y){
		if (x+1 > weight) return false;
		if ( !isEmpty(x+1, y) ) return false;
		else return true;
	}
	
	/**возвращает true если идти вперед наверх можно (впереди пусто)
	 * 
	 */
	public boolean top(int x, int y){
		if (y+1 > hight) return false;
		if ( !isEmpty(x, y+1) ) return false;
		else return true;
	}
	
	/**возвращает true если идти вниз можно (внизу пусто)
	 * 
	 */
	public boolean bottom(int x, int y){
		if (y-1 < 0) return false;
		if ( !isEmpty(x, y-1) ) return false;
		else return true;
	}
	
	/**задание ширины 
	 * 
	 */
	public void setWeight(int newWeight){
		
		if (newWeight < 0) System.out.println("Невозможно изменить ширину!");
		else {
			boolean[][] exmaze  = maze;
			
			maze = new boolean[newWeight][hight]; 
			
			for (int i=0; i<Integer.min(newWeight, weight); i++){ 
				for(int j=0; j<hight; j++){
					maze[i][j] = exmaze[i][j];
				}
			}
			
			if (newWeight > weight) {
				for (int i=weight; i<newWeight; i++){ 
					for(int j=0; j<hight; j++){
						maze[i][j] = false;
					}
				}
			}
			
			weight = newWeight; 
				
		}
	}
	
	/**ширина
	 * 
	 */
	public int getWeight(){
		return weight;
	}
	
	/**задание высоты
	 * 
	 */
	public void setHight(int newHight){
		if (newHight < 0) System.out.println("Невозможно изменить ширину!");
		else {
			boolean[][] exmaze = maze;
			maze = new boolean[weight][newHight]; 
			
			for (int i=0; i<Integer.min(newHight, hight); i++){ 
				for(int j=0; j<weight; j++){
					maze[j][i] = exmaze[j][i];
				}
			}
			
			if (newHight > hight) {
				for (int i=hight; i<newHight; i++){ //
					for(int j=0; j<weight; j++){
						maze[j][i] = false;
					}
				}
			}
			hight = newHight; 				
		}
		
	}
	
	/**высота
	 * 
	 */
	public int getHight(){
		return hight;
	}
	
	/**координаты начала, их задание
	 * 
	 */
	public void setStartX(int x){
		if ((-1 < x) & (x < weight)) startX = x;
		else System.out.println("Выход за пределы лабиринта");
	}

	public void setStartY(int y){
		if ((-1 < y) & (y < hight)) startY = y;
		else System.out.println("Выход за пределы лабиринта");
	}
        
        /**координаты начала, их задание
	 * 
	 */
	public int getStartX(){
		return startX;
	}

	public int getStartY(){
		return startY;
	}
	
	/**координаты конца, их задание
	 * 
	 */
	public void setFinishX(int x){
		if ((-1 < x) & (x < weight)) finishX = x;
		else System.out.println("Выход за пределы лабиринта");
	}

	public void setFinishY(int y){
		if ((-1 < y) & (y < hight)) finishY = y;
		else System.out.println("Выход за пределы лабиринта");
	}
        
        public int getFinishX(){
		return finishX;
	}

	public int getFinishY(){
		return finishY;
	}
	
}
