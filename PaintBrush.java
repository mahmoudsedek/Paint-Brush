import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class PaintBrush extends Applet implements Runnable {

    private String selectedColor = "black";
    private boolean isFill = false;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int prevX, prevY;
    Vector<GraphicsObject> graphicsVector = new Vector<>();
    Vector<Object> listenerVector = new Vector<>();
    Thread th = new Thread();

    public void init() {
        th.start();

        // Define and Add Blue Color Button
        Button blueColor = new Button("Blue");
        blueColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedColor = "blue";
            }
        });
        add(blueColor);

        // Define and Add Red Color Button
        Button redColor = new Button("Red");
        redColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedColor = "red";
            }
        });
        add(redColor);

        // Define And Add Green Color Button
        Button greenColor = new Button("Green");
        greenColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedColor = "green";
            }
        });
        add(greenColor);

        // Define and Add Solid Checkbox
        Checkbox isFillCheckBox = new Checkbox("Solid", isFill);
        isFillCheckBox.setBounds(100, 100, 50, 50);
        isFillCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    isFill = true;
                } else {
                    isFill = false;
                }
            }
        });
        add(isFillCheckBox);

        // Define and Add Circle Button
        Button circleBtn = new Button("Circle");
        circleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawCircle();
            }
        });
        add(circleBtn);

        // Define and Add Rectangle Button
        Button rectBtn = new Button("Rectangle");
        rectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawRectangle();
            }
        });
        add(rectBtn);

        // Define and Add Line Button
        Button lineBtn = new Button("Line");
        lineBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawLine();
            }
        });
        add(lineBtn);

        //Define and Add FreeHand Button
        Button FreeBtn = new Button("FreeHand");
        FreeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawFree();
            }
        });
        add(FreeBtn);

        //Define and Add Eraser Button
        Button EraserBtn = new Button("Eraser");
        EraserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleEraser();
            }
        });
        add(EraserBtn);

        // Define and Add Clear Button
        Button clearBtn = new Button("Clear");
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphicsVector.clear();
                repaint();
            }
        });
        add(clearBtn);
    }

    public void drawRectangle() {
        clearAllListeners();
        System.out.println("Draw Rectangle Called With [" + selectedColor + "] color selected and Fill: [" + isFill + "]");
        // Define And Add Mouse Events
        class RectangleDrawerMousePresser implements MouseListener {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                GraphicsObject graphicsObject = new GraphicsObject(getSelectedColorToDraw(selectedColor));
                graphicsObject.isRectangle = true;
                graphicsObject.isFill = isFill;
                if ((x1 <= x2) && (y1 <= y2)) {
                    graphicsObject.setPoints(x1, y1, (x2 - x1), (y2 - y1));
                    graphicsVector.add(graphicsObject);
                } else if ((x1 >= x2) && (y1 <= y2)) {
                    graphicsObject.setPoints(x2, y1, (x1 - x2), (y2 - y1));
                    graphicsVector.add(graphicsObject);
                } else if ((x1 >= x2) && (y1 >= y2)) {
                    graphicsObject.setPoints(x2, y2, (x1 - x2), (y1 - y2));
                    graphicsVector.add(graphicsObject);
                } else if ((x1 <= x2) && (y1 >= y2)) {
                    graphicsObject.setPoints(x1, y2, (x2 - x1), (y1 - y2));
                    graphicsVector.add(graphicsObject);
                } else {

                    if ((x1 <= x2) && (y1 <= y2)) {
                        graphicsObject.setPoints(x1, y1, (x2 - x1), (y2 - y1));
                        graphicsVector.add(graphicsObject);
                    } else if ((x1 >= x2) && (y1 <= y2)) {
                        graphicsObject.setPoints(x2, y1, (x1 - x2), (y2 - y1));
                        graphicsVector.add(graphicsObject);
                    } else if ((x1 >= x2) && (y1 >= y2)) {
                        graphicsObject.setPoints(x2, y2, (x1 - x2), (y1 - y2));
                        graphicsVector.add(graphicsObject);
                    } else if ((x1 <= x2) && (y1 >= y2)) {
                        graphicsObject.setPoints(x1, y2, (x2 - x1), (y1 - y2));
                        graphicsVector.add(graphicsObject);
                    }
                }
                repaint();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        }
        RectangleDrawerMousePresser rectangleDrawerMousePresser = new RectangleDrawerMousePresser();
        listenerVector.add(rectangleDrawerMousePresser);
        addMouseListener(rectangleDrawerMousePresser);

        repaint();
    }

    public void drawCircle() {
        clearAllListeners();
        System.out.println("Draw Circle Called With [" + selectedColor + "] color selected and Fill: [" + isFill + "]");
        //Color usedColor = getSelectedColorToDraw(selectedColor);
        class CircleDrawerMousePresser implements MouseListener {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                GraphicsObject graphicsObject = new GraphicsObject(getSelectedColorToDraw(selectedColor));
                graphicsObject.isCircle = true;
                graphicsObject.isFill = isFill;
                if ((x1 <= x2) && (y1 <= y2)) {
                    graphicsObject.setPoints(x1, y1, (x2 - x1), (y2 - y1));
                    graphicsVector.add(graphicsObject);
                } else if ((x1 >= x2) && (y1 <= y2)) {
                    graphicsObject.setPoints(x2, y1, (x1 - x2), (y2 - y1));
                    graphicsVector.add(graphicsObject);
                } else if ((x1 >= x2) && (y1 >= y2)) {
                    graphicsObject.setPoints(x2, y2, (x1 - x2), (y1 - y2));
                    graphicsVector.add(graphicsObject);
                } else if ((x1 <= x2) && (y1 >= y2)) {
                    graphicsObject.setPoints(x1, y2, (x2 - x1), (y1 - y2));
                    graphicsVector.add(graphicsObject);
                } else {

                    if ((x1 <= x2) && (y1 <= y2)) {
                        graphicsObject.setPoints(x1, y1, (x2 - x1), (y2 - y1));
                        graphicsVector.add(graphicsObject);
                    } else if ((x1 >= x2) && (y1 <= y2)) {
                        graphicsObject.setPoints(x2, y1, (x1 - x2), (y2 - y1));
                        graphicsVector.add(graphicsObject);
                    } else if ((x1 >= x2) && (y1 >= y2)) {
                        graphicsObject.setPoints(x2, y2, (x1 - x2), (y1 - y2));
                        graphicsVector.add(graphicsObject);
                    } else if ((x1 <= x2) && (y1 >= y2)) {
                        graphicsObject.setPoints(x1, y2, (x2 - x1), (y1 - y2));
                        graphicsVector.add(graphicsObject);
                    }
                }
                repaint();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        }
        CircleDrawerMousePresser circleDrawerMousePresser = new CircleDrawerMousePresser();
        listenerVector.add(circleDrawerMousePresser);
        addMouseListener(circleDrawerMousePresser);

        repaint();
    }


    public void drawLine() {
        clearAllListeners();
        System.out.println("Draw Line Called With [" + selectedColor + "] color selected");
        Color usedColor = getSelectedColorToDraw(selectedColor);
        Graphics graphic = getGraphics();
        graphic.setColor(usedColor);
        // Define And Add Mouse Events
        class LineDrawerMousePresser implements MouseListener {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                GraphicsObject graphicsObject = new GraphicsObject(getSelectedColorToDraw(selectedColor));
                graphicsObject.x1 = x1;
                graphicsObject.y1 = y1;
                graphicsObject.x2 = x2;
                graphicsObject.y2 = y2;
                graphicsVector.add(graphicsObject);
                repaint();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        }
        LineDrawerMousePresser lineDrawerPresser = new LineDrawerMousePresser();
        listenerVector.add(lineDrawerPresser);
        addMouseListener(lineDrawerPresser);

        class MouseDragged implements MouseMotionListener {
            public void mouseMoved(MouseEvent e2) {
            }

            public void mouseDragged(MouseEvent e2) {
                x2 = e2.getX();
                y2 = e2.getY();
                repaint();
            }
        }
        MouseDragged mouseDragged = new MouseDragged();
        listenerVector.add(mouseDragged);
        addMouseMotionListener(mouseDragged);
        repaint();
    }

    public void drawFree() {
        clearAllListeners();
        System.out.println("Free Hand Called With [" + selectedColor + "] color selected");
        Color usedColor = getSelectedColorToDraw(selectedColor);
        Graphics graphic = getGraphics();
        graphic.setColor(usedColor);
        // Define And Add Mouse Events
        class FreeHandMousePresser implements MouseListener {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                int width = getSize().width;
                int height = getSize().height;
                if (x1 > width - 53) {
                    if (y1 > height - 53)
                        repaint();
                } else if (x1 > 3 && x1 < width - 56 && y1 > 3 && y1 < height - 3) {
                    prevX = x1;
                    prevY = y1;
                    repaint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                repaint();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        }
        FreeHandMousePresser freeHandPresser = new FreeHandMousePresser();
        listenerVector.add(freeHandPresser);
        addMouseListener(freeHandPresser);
        class MouseDragged implements MouseMotionListener {
            public void mouseMoved(MouseEvent e2) {
            }

            public void mouseDragged(MouseEvent e2) {
                int x = e2.getX();
                int y = e2.getY();
                if (x < 3) x = 3;
                if (x > getSize().width - 57) x = getSize().width - 57;
                if (y < 3) y = 3;
                if (y > getSize().height - 4) y = getSize().height - 4;
                GraphicsObject object = new GraphicsObject(getSelectedColorToDraw(selectedColor));
                object.setPoints(prevX, prevY, x, y);
                graphicsVector.add(object);
                prevX = x;
                prevY = y;
                repaint();
            }
        }
        MouseDragged mouseDragged = new MouseDragged();
        listenerVector.add(mouseDragged);
        addMouseMotionListener(mouseDragged);
        repaint();
    }

    public void handleEraser() {
        clearAllListeners();
        System.out.println("Eraser Called");
        Graphics graphic = getGraphics();
        graphic.setColor(Color.white);
        // Define And Add Mouse Events
        class EraserMousePresser implements MouseListener {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                GraphicsObject object = new GraphicsObject(getSelectedColorToDraw("white"));
                x1 = e.getX();
                y1 = e.getY();
                graphic.fillRect(x1, y1, 10, 10);
                graphicsVector.add(object);
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                repaint();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        }
        EraserMousePresser eraseMousePress = new EraserMousePresser();
        listenerVector.add(eraseMousePress);
        addMouseListener(eraseMousePress);

        class MouseDragged implements MouseMotionListener {
            public void mouseMoved(MouseEvent e2) {
            }

            public void mouseDragged(MouseEvent e2) {
                GraphicsObject object = new GraphicsObject(getSelectedColorToDraw("white"));
                object.isRectangle = true;
                object.isFill = true;
                x1 = e2.getX();
                y1 = e2.getY();
                object.setPoints(x1, y1, 10, 10);
                graphicsVector.add(object);
                repaint();
            }
        }
        MouseDragged mouseDragged = new MouseDragged();
        listenerVector.add(mouseDragged);
        addMouseMotionListener(mouseDragged);
        repaint();

    }

    public void paint(Graphics graphics) {
        try {
            for (GraphicsObject graphicsObject : graphicsVector) {
                graphics.setColor(graphicsObject.color);

                if (graphicsObject.isCircle) {
                    if (graphicsObject.isFill) {
                        graphics.fillOval(graphicsObject.x1, graphicsObject.y1, graphicsObject.x2, graphicsObject.y2);
                    } else {
                        graphics.drawOval(graphicsObject.x1, graphicsObject.y1, graphicsObject.x2, graphicsObject.y2);
                    }
                } else if (graphicsObject.isRectangle) {
                    if (graphicsObject.isFill) {
                        graphics.fillRect(graphicsObject.x1, graphicsObject.y1, graphicsObject.x2, graphicsObject.y2);
                    } else {
                        graphics.drawRect(graphicsObject.x1, graphicsObject.y1, graphicsObject.x2, graphicsObject.y2);
                    }
                } else if (graphicsObject.isFreehand) {
                    x1 = graphicsObject.x1;
                    y1 = graphicsObject.y1;
                    if (x1 < 3)
                        x1 = 3;
                    if (x1 > getSize().width - 57)
                        x1 = getSize().width - 57;

                    if (y1 < 3)
                        y1 = 3;
                    if (y1 > getSize().height - 4)
                        y1 = getSize().height - 4;
                    graphics.drawLine(x2, y2, x1, y1);
                    x2 = x1;
                    y2 = y1;
                } else if (graphicsObject.isEraser) {
                    graphics.drawRect(graphicsObject.x1, graphicsObject.y1, 3, 3);
                } else {
                    graphics.drawLine(graphicsObject.x1, graphicsObject.y1, graphicsObject.x2, graphicsObject.y2);
                }
            }
            th.sleep(150);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public void run() {
		/*try
			{
					th.sleep(1000);
			}catch(InterruptedException ie)
			{
				 ie.printStackTrace();
			}*/
        repaint();

    }


    class GraphicsObject {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        Color color;
        boolean isFill;
        boolean isCircle;
        boolean isRectangle;
        boolean isFreehand;
        boolean isEraser;

        public GraphicsObject(Color color) {
            this.color = color;
        }

        public void setPoints(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public Color getSelectedColorToDraw(String color) {
        if (color == "red") {
            return Color.red;
        } else if (color == "blue") {
            return Color.blue;
        } else if (color == "green") {
            return Color.green;
        } else if (color == "white") {
            return Color.white;
        }
        return Color.BLACK;
    }

    public void clearAllListeners() {
        for (Object listener : listenerVector) {
            if (listener instanceof MouseListener) {
                removeMouseListener((MouseListener) listener);
            } else if (listener instanceof MouseMotionListener) {
                removeMouseMotionListener((MouseMotionListener) listener);
            }
        }
        listenerVector.clear();
        repaint();
    }
}
