package gameobjects;

/**

 Représente les différentes catégories de textures pour que nous puissions les gérer via le Gestionnaire de Textures.<p>
 STATIC pour les blocs qui ne sont pas censés bouger.<br>
 ENTITY pour les blocs qui sont censés bouger et qui pourraient contenir une animation.<br>
 OTHER pour tout ce qui ne rentre pas dans les catégories précédentes.<br>
 */

public enum ObjectTypes {
    STATIC, ENTITY, OTHER
}