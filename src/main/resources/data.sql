--DROP TABLE IF EXISTS snippets;
--CREATE TABLE snippets (id number, language varchar(20), code varchar(500));
INSERT INTO snippets ( language, code) VALUES ('Python', 'print(Hello, World!)');
INSERT INTO snippets ( language, code) VALUES ('Python', 'def add(a, b):\n    return a + b');
INSERT INTO snippets ( language, code) VALUES ('Python', 'class Circle:\n    def __init__(self, radius):\n        self.radius = radius\n\n    def area(self):\n        return 3.14 * self.radius ** 2');
INSERT INTO snippets ( language, code) VALUES ('JavaScript', 'function multiply(a, b) {\n    return a * b;\n}');
INSERT INTO snippets ( language, code) VALUES ('JavaScript', 'const square = num => num * num;');
INSERT INTO snippets ( language, code) VALUES ('Java', 'public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}');
INSERT INTO snippets ( language, code) VALUES ('Java', 'public class Rectangle {\n    private int width;\n    private int height;\n\n    public Rectangle(int width, int height) {\n        this.width = width;\n        this.height = height;\n    }\n\n    public int getArea() {\n        return width * height;\n    }\n}');
