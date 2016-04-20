/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.util.Objects;

/**
 *
 * @author ilyab
 */
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
         * @param x задает значение поля X
         * @param y задеат значение поля Y
         */
        public Point(final Integer x, final Integer y) {
            X = x;
            Y = y;
        }

        /**
         * Конструктор копирования инициализирует значения полей из копии
         * @param ob оригинал объекта для копирования
         */
        public Point(final Point ob){
            X = ob.X;
            Y = ob.Y;
        }
        
        /**
         * Сравнивает обекты типа Point
         * @param ob объект для сравнения
         * @return возвращает true, если объекты равны, иначе false
         */
        public boolean equals(final Point ob) {
            if(ob!=null)
            return Objects.equals(X, ob.X) && Objects.equals(Y, ob.Y);
            return false;
        }

        /**
         * Задает параметр X
         * @param x координата X
         */
        public void setX(final Integer x) {
            X = x;
        }

        /**
         * Задает параметр Y
         * @param y координата Y
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
        
        public static Point plus(final Point p1, final Point p2) {
            return new Point(p1.X + p2.X, p1.Y + p2.Y);
        }
        
        public static Point minus(final Point p1, final Point p2) {
            return new Point(p1.X - p2.X, p1.Y - p2.Y);
        }
    }
