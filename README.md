# 김민식 학번 202130104

# 06월 14일 강의

## 자바의 입출력 스트림

### 입출력 장치와 자바 응용 프로그램 연결

- **입력 스트림**: 입력 장치로부터 자바 프로그램으로 데이터를 전달하는 객체
- **출력 스트림**: 자바 프로그램에서 출력 장치로 데이터를 보내는 객체

**특징:**
- **입출력 스트림 기본 단위**: 바이트
- 단방향 스트림, 선입선출 구조

자바 프로그램 개발자는 직접 입력 장치에서 읽지 않고 입력 스트림을 통해 읽으며, 스크린 등 출력 장치에 직접 출력하지 않고 출력 스트림에 출력하면 됩니다.

### 자바의 입출력 스트림 종류

#### 문자 스트림
- **문자만 입출력하는 스트림**
- 문자가 아닌 바이너리 데이터는 스트림에서 처리하지 못함
- 문자가 아닌 데이터를 문자 스트림으로 출력하면 깨진 기호가 출력됨
- 바이너리 파일을 문자 스트림으로 읽으면 읽을 수 없는 바이트가 생겨서 오류 발생

**예:** 텍스트 파일을 읽는 입력 스트림

#### 바이트 스트림
- **입출력 데이터를 단순 바이트의 흐름으로 처리**
- 문자 데이터든 바이너리 데이터든 상관없이 처리 가능

**예:** 바이너리 파일을 읽는 입력 스트림
![이미지 파일](src/1학기%20기말고사/06월%2014일%20파일/1.png)

### 스트림 연결

- 여러 개의 스트림을 연결하여 사용할 수 있습니다.

**예** 키보드에서 문자를 입력받기 위해 `System.in`과 `InputStreamReader`를 연결한 코드:
```java
InputStreamReader rd = new InputStreamReader(System.in);
while(true) {
    int c = rd.read(); // 협력 스트림으로부터 키 입력
    if (c == -1 || c == '\n') // 입력된 문자가 스트림의 끝을 만나거나 줄바꿈 문자인 경우
        break;
    System.out.print((char) c); // 입력된 문자를 출력
}
```

- 문자 스트림으로 텍스트 파일 읽기
- 텍스트 파일을 읽기 위해 문자 스트림 FileReader 클래스를 이용합니다.

- 파일 입력 스트림 생성(파일 열기):

- 스트림을 생성하고 파일을 열어 스트림과 연결
```java
FileReader in = new FileReader("filename.txt");
```

- 파일 읽기:

## **문자 하나씩 파일에서 읽음**
```java
int c;
while((c = in.read()) != -1) { // 문자를 읽음. 파일 끝까지 반복
    System.out.print((char) c); // 읽은 문자를 화면에 출력
}
```

## **스트림 닫기:**

- 스트림이 더 이상 필요 없으면 닫아야 함. 닫힌 스트림에서는 읽을 수 없음
```java
in.close(); // 스트림 닫기

```

- 파일 입출력과 예외 처리
- 파일 입출력 동안 예외가 발생할 수 있습니다.

## **스트림 생성 동안 FileNotFoundException 발생 가능**
```java
try {
    FileReader in = new FileReader("filename.txt");
} catch (FileNotFoundException e) {
    e.printStackTrace(); // 파일을 찾지 못한 경우 처리
}

```

- 파일 읽기, 쓰기, 닫기를 하는 동안 IOException 발생 가능
```java
try {
    FileReader in = new FileReader("filename.txt");
    int c;
    while((c = in.read()) != -1) {
        System.out.print((char) c);
    }
    in.close();
} catch (IOException e) {
    e.printStackTrace(); // 입출력 오류 발생 시 처리
}

```

- 주의사항: try-catch 블록은 반드시 필요합니다.
```java
try {
    FileReader in = new FileReader("filename.txt");
    int c;
    while((c = in.read()) != -1) {
        System.out.print((char) c);
    }
    in.close();
} catch (FileNotFoundException e) {
    e.printStackTrace(); // 파일을 찾지 못한 경우 처리
} catch (IOException e) {
    e.printStackTrace(); // 입출력 오류 발생 시 처리
}
```

## 문자 스트림으로 텍스트 파일 쓰기

텍스트 파일에 쓰기 위해 문자 스트림 `Free` 클래스 이용

- 파일 열기
- `there the 1004` 쓰기
- `o 크기 쓰기` 쓰기
- 스트림 닫기

**주의:** 스트림을 닫지 않으면 기록할 수 없습니다.

## 바이트 스트림으로 바이너리 파일 쓰기

### 바이너리 값을 파일에 저장하기
- 프로그램 내의 변수, 배열, 버퍼에 든 바이너리 값을 파일에 그대로 기록

### 파일출력 스트림 생성( 파일열기 )
- 스트림을 생성하고 파일을 열어 스트림과 연결
```java
try (FileOutputStream fos = new FileOutputStream("파일명.bin")) {
    // 파일 출력 스트림 생성
    // "파일명.bin" 파일을 쓰기 모드로 열기
}
```
### 파일출력 스트림 생성( 파일쓰기 )
```java
    byte[] data = {0x0A, 0x33, (byte) 0x83, (byte) 0x84, (byte) 0xFF, 0x18};
    fos.write(data);
    // 바이너리 데이터(data 배열)를 파일에 쓰기
```

### 파일출력 스트림 생성( 파일닫기 )
```java
    // 스트림 닫기 (try-with-resources 문 사용으로 자동으로 닫힘)
 catch (IOException e) {
    e.printStackTrace();
}
```

## File 클래스

File 클래스는 파일의 경로명 및 속성을 다루는 클래스입니다. 파일 이름 변경, 삭제, 디렉터리 생성, 파일 크기 등의 파일 관리 작업을 수행할 수 있습니다. File 클래스는 파일의 내용을 직접 읽거나 쓰는 기능은 없으며, 파일 입출력은 파일 입력 및 출력 스트림을 이용하여 수행해야 합니다.

### File 클래스의 주요 기능

1. **File 객체 생성**

   파일 객체를 생성할 때는 파일의 경로명을 인자로 넘겨주어야 합니다.

```java
   // 파일 경로명을 사용하여 File 객체 생성
   File file1 = new File("C:\\Temp\\test.txt");
```

- 디렉터리와 파일명을 분리하여 생성자 호출

- 디렉터리 경로와 파일명을 나누어서 File 객체를 생성할 수 있습니다.
```java
// 디렉터리 경로와 파일명을 분리하여 File 객체 생성
File file2 = new File("C:\\Temp", "test.txt");

```
![이미지 파일](src/1학기%20기말고사/06월%2014일%20파일/2.png)
### **주의사항**
File 객체를 생성하는 것만으로는 실제 파일이나 디렉터리가 생성되지 않습니다. 파일이나 디렉터리를 생성하려면 추가적인 작업이 필요합니다.
File 객체를 이용하여 파일 관련 작업을 할 때는 파일의 존재 여부나 접근 권한 등을 추가적으로 체크해야 합니다.






# 6월 07일 강의

## 스윙 컴포넌트 그리기: `paintComponent()`

```java
public void paintComponent(Graphics g)
```
## 스윙의 페인팅 기본
- 모든 컴포넌트는 자신의 모양을 스스로 그린다.
- 컨테이너는 자신을 그린 후 그 위에 자식 컴포넌트들에게 그리기 지시를 한다.
- 모든 스윙 컴포넌트는 자신의 모양을 그리는 `paintComponent()` 메소드를 보유하고 있다.

## `paintComponent()` 메소드
- 스윙 컴포넌트가 자신의 모양을 그리는 메소드이다.
- Component의 메소드로 스윙 컴포넌트가 이 메소드를 오버라이딩한다.

### 언제 호출되는가?
- 컴포넌트가 `그려져야 하는 시점마다` 호출된다.
  - 크기가 변경되거나 위치가 변경되거나, 컴포넌트가 가려졌던 것이 사라지는 등

### 매개변수인 `Graphics` 객체
- 그래픽 컨텍스트: 컴포넌트 그리기에 필요한 도구를 제공하는 객체이다.
- 자바 플랫폼에 의해 공급된다.

### 주요 기능
- 색상 설정
- 도형 그리기
- 이미지 그리기 등의 메소드 제공

## paintComponent()의 오버라이딩과 JPanel

### paintComponent(Graphics g)의 오버라이딩

- `paintComponent(Graphics g)` 메서드는 그래픽 컨텍스트를 이용하여 컴포넌트를 그릴 때 사용되는 메서드입니다.
- 개발자가 `JComponent`를 상속받아 새로운 컴포넌트를 설계할 때 주로 사용됩니다.
- 기존 컴포넌트의 모양을 변화시키거나, 새로운 모양을 만들고자 할 때 유용하게 활용됩니다.
- 예시 코드:
  ```java
  class MComponent extends JComponent {
      @Override
      public void paintComponent(Graphics g) {
          super.paintComponent(g);
          // 필요한 그리기 코드 작성
      }
  }

## JPanel
- JPanel은 비어 있는 컨테이너로, 다양한 GUI를 구성할 수 있는 캔버스로 활용됩니다. 개발자가 임의의 모양을 가지는 패널을 만들기 위해 JPanel을 상속받아 사용됩니다. JPanel을 이용하면 다양한 구성요소들을 자유롭게 배치하고 사용할 수 있습니다.

## 예제 11-1: JPanel을 상속받은 패널에 도형 그리기

- JPanel을 상속받아 패널을 구성하고, 이곳에 그림과 같은 3개의 도형을 그리는 예제입니다.

### 코드 예제

```java
import javax.swing.*;
import java.awt.*;

public class PaintJPanelEx extends JFrame {
    public PaintJPanelEx() {
        setTitle("Panel paintComponent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(250, 200);
        setVisible(true);
    }

    // JPanel을 상속받는 새 패널 구현
    class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE); // 파란색 선택
            g.drawRect(10, 10, 50, 50);
            g.drawRect(50, 50, 50, 50);
            g.setColor(Color.MAGENTA); // 마젠타색 선택
            g.drawRect(90, 90, 50, 50);
        }
    }

    public static void main(String[] args) {
        new PaintJPanelEx();
    }
}
```

### PaintJPanelEx 클래스 설명

- `PaintJPanelEx` 클래스는 `JFrame`을 상속받아 GUI 창을 생성합니다.
- 생성자에서는 제목을 설정하고, 기본 닫기 동작을 정의하며, 커스텀 패널인 `MyPanel`을 컨텐트 패널로 설정합니다.

### MyPanel 클래스 설명

- `MyPanel` 클래스는 `JPanel`을 상속받아 새 패널을 구현합니다.
- `paintComponent` 메서드를 오버라이드하여 도형을 그립니다.
  - `super.paintComponent(g)`를 호출하여 패널을 초기화합니다.
  - `g.setColor(Color.BLUE)`로 파란색을 선택하고, `g.drawRect(10, 10, 50, 50)`와 `g.drawRect(50, 50, 50, 50)`로 두 개의 파란색 사각형을 그립니다.
  - `g.setColor(Color.MAGENTA)`로 마젠타색을 선택하고, `g.drawRect(90, 90, 50, 50)`로 마젠타색 사각형을 그립니다.

## main 메서드 설명

- `main` 메서드에서는 `PaintJPanelEx` 인스턴스를 생성하여 프로그램을 시작합니다.

## 그래픽 기반 GUI 프로그래밍

### 그래픽 기반 GUI 프로그래밍이란?

`그래픽 기반 GUI 프로그래밍은 스윙 컴포넌트에 의존하지 않고 선, 원, 이미지 등을 이용하여 직접 화면을 구성하는 방법을 말합니다.`

### 그래픽 기반 GUI 프로그래밍의 필요성

- 컴포넌트의 한계를 극복하고 차트, 게임 등의 자유로운 콘텐츠를 표현할 수 있습니다.
- 그래픽은 컴포넌트에 비해 화면 출력 속도가 빠르므로, 성능이 중요한 애플리케이션에서 유용합니다.
- 스윙 컴포넌트들은 모두 그래픽으로 작성되어 있기 때문에, 그래픽에 대한 학습은 GUI의 기반 기술을 이해하는데 도움이 됩니다.
- 그래픽을 이용하여 개발자 자신만의 컴포넌트를 개발할 수 있습니다.

## Graphics와 문자열 출력

### Graphics의 기능

- `색상 선택하기`
- `문자열 그리기`
- `도형 그리기`
- `도형 칠하기`
- `이미지 그리기`
- `클리핑`

### 문자열 출력을 위한 Graphics 메소드

```java
void drawString(String str, int x, int y)
```
- *str 문자열을 (x, y) 위치에 그립니다. 현재 Graphics 설정된 색과 폰트로 문자열이 출력됩니다.*

```java
Graphics g;
g.drawString("자바는 재밌다", 30, 30); // (30, 30) 위치에 문자열 출력
```

### 그래픽의 색과 폰트

- 색: `Color` 클래스를 사용합니다. 자바의 색은 (Red, Green, Blue) 성분으로 구성되며, 각 성분은 0에서 255(8비트) 사이의 정수값입니다. 예를 들어, 빨간색은 `new Color(255, 0, 0)`으로 표현됩니다.

- 폰트: `Font` 클래스를 사용합니다. `Font(String fontFace, int style, int size)` 생성자를 사용하여 폰트를 설정합니다. `fontFace`는 폰트 이름, `style`은 폰트 스타일 (BOLD, ITALIC, PLAIN 중 하나), `size`는 폰트 크기 (픽셀 단위)를 나타냅니다.

```java
raphics에 색과 폰트 설정
void setColor(Color color)
```
- 그래픽의 색을 설정합니다. 이 색은 그릴 때 사용됩니다.

```java
void setFont(Font font)
```
- 그래픽에 사용할 폰트를 설정합니다.

예시:
```java
Graphics g;
Font f = new Font("Arial", Font.ITALIC, 30);
g.setFont(f);
g.setColor(Color.RED);
g.drawString("How much", 30, 30);
```
- 위 예시에서는 "How much"라는 문자열을 "Arial" 폰트의 빨간색으로 (30, 30) 위치에 출력합니다.

## 도형 그리기와 칠하기

### < 도형 그리기 >

### 선, 타원, 사각형, 둥근 모서리 사각형, 원호, 다각형 그리기

- **선(Line) 그리기:** `drawLine(int x1, int y1, int x2, int y2)` 메서드를 사용하여 (x1, y1)에서 (x2, y2)까지 선을 그립니다.

- **타원(Oval) 그리기:** `drawOval(int x, int y, int width, int height)` 메서드를 사용하여 (x, y)를 왼쪽 상단 모서리로 하는 타원을 그립니다.

- **사각형(Rectangle) 그리기:** `drawRect(int x, int y, int width, int height)` 메서드를 사용하여 (x, y)를 왼쪽 상단 모서리로 하는 사각형을 그립니다.

- **둥근 모서리 사각형(Round Rectangle) 그리기:** `drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)` 메서드를 사용하여 (x, y)를 왼쪽 상단 모서리로 하는, 너비가 width이고 높이가 height인 사각형의 모서리를 둥글게 그립니다. `arcWidth`와 `arcHeight`는 모서리 원의 수평 반지름과 수직 반지름입니다.

- **원호(Arc) 그리기:** `drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)` 메서드를 사용하여 (x, y)를 시작점으로 하는 원호를 그립니다. startAngle은 시작 각도이고, arcAngle은 호의 각도입니다.

- **다각형(Polygon) 그리기:** `drawPolygon(int[] xPoints, int[] yPoints, int nPoints)` 메서드를 사용하여 주어진 점(xPoints, yPoints)들을 연결하여 다각형을 그립니다.

## 도형 칠하기

도형을 그린 후 내부를 채우는 기능입니다. 그리기 메서드 명에서 `draw` 대신 `fill`을 사용하여 내부를 채울 수 있습니다. 예를 들어, `drawRect()` 대신 `fillRect()`를 사용하여 사각형 내부를 채울 수 있습니다.

## Graphics의 원호와 폐다각형 그리기 메소드

### 원호(Arc) 그리기

```java
void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
```

#### 원호의 시작 각도

- **startAngle**: 원호의 시작 각도입니다. 3시 방향이 0도의 기준점입니다.

#### 원호의 각도

- **arcAngle**: 원호의 각도입니다.

#### (x, y)에서 width와 height 크기의 사각형에 내접하는 원호 그리기

- 원본을 유지하면서, startAngle 지점에서 arcAngle 각도만큼 원호를 그립니다. arcAngle이 양수이면 반시계 방향으로, 음수이면 시계 방향으로 그립니다.

### 폐다각형(직선으로 이루어진 다각형) 그리기
```java
void drawPolygon(int[] xPoints, int[] yPoints, int n)
```
- xPoints, yPoints 배열에 저장된 점들 중 n개를 연결하는 폐다각형을 그립니다.

- (xPoints[0], yPoints[0]), (xPoints[1], yPoints[1]), ..., (xPoints[n-1], yPoints[n-1]), (xPoints[0], yPoints[0])의 점들을 순서대로 연결합니다.

예제:
```java
class MyPanel extends JPanel ( public void paintComponent/Graphics g) ( super paintComponent(g): g setColor Color RED g.drawArc(20,100,80,80,90,270)
class MyPanel extends JPanel ( public void paintComponent(Graphics g) ( super paintComponent(g); g.setColor(Color.RED);
int x= (80,40,80,120); int ly (40,120,200,120); g.drawPolygon(x, y, 4);
```

## 스윙에서 이미지를 그리는 2가지 방법

1. **JLabel을 이용한 이미지 그리기**
    - 이미지를 JLabel을 통해 그립니다.
    ```java
    ImageIcon image = new ImageIcon("images/apple.jpg");
    JLabel label = new JLabel(image);
    panel.add(label);
    ```
    - **장점**: 이미지 그리기가 간편합니다.
    - **단점**: 이미지의 원본 크기대로 그려지므로 이미지 크기를 조절할 수 없습니다.

2. **Graphics의 drawImage()로 이미지 출력**
    - 이미지의 일부분을 그릴 수 있고, 원본 크기와 다르게 그릴 수 있습니다.
    - **장점**: 이미지의 일부분 등 이미지의 크기와 위치를 조절할 수 있습니다.
    - **단점**: 컴포넌트로 관리할 수 없으며, 이미지의 위치나 크기를 적절히 조절하는 코딩이 필요합니다.

### 이미지 그리기 샘플 코드

#### 이미지 로딩
```java
ImageIcon icon = new ImageIcon("image/image0.jpg");
Image img = icon.getImage();

// (20, 20) 위치에 원본 크기로 그리기 (고정 크기임)
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 20, 20, this);
}

// (20, 20) 위치에 100x100 크기로 그리기 (고정 크기임)
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 20, 20, 100, 100, this);
}

// 이미지를 패널에 꽉 차도록 그리기 (가변 크기임)
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 20, 20, 100, 100, this);
}

// JPanel의 크기로 그리기 (가변 크기임)
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
}
```

## repaint()

- **설명**: 자바의 모든 컴포넌트가 가지고 있는 메소드로, 자바 플랫폼에게 컴포넌트를 강제로 그리도록 지시합니다.
- `repaint()`가 호출되면, 자바 플랫폼은 컴포넌트의 `paintComponent()` 메소드를 호출합니다.

### `repaint()`를 호출해야 하는 경우:

1. 개발자가 컴포넌트를 다시 그리고자 할 때:
   - 프로그램에서 컴포넌트의 모양과 위치를 변경하고 즉시 화면에 반영해야 하는 상황에서 사용합니다.
   - 컴포넌트가 변경된 위치에 변경된 모양으로 즉시 다시 그려져야 할 때 사용됩니다.
   - `repaint()`는 자바 플랫폼에게 즉시 컴포넌트를 다시 그리도록 지시합니다.

### 최적의 사용 방법:

- 부모 컴포넌트부터 다시 그리는 것이 좋습니다.
- `component.repaint()`가 호출될 때:
  - 컴포넌트는 새로운 위치에 다시 그려지지만, 이전 모양은 이전 위치에 그대로 남아 있습니다.

### 부모 컴포넌트에서 `repaint()`를 호출하는 경우:

- 부모 컴포넌트에서 `repaint()`가 호출되면:
  - 부모 컨테이너의 모든 내용이 지워지고, 자식 컴포넌트들이 다시 그려집니다.
  - 결과적으로 컴포넌트의 이전 모양이 지워지고, 변경된 크기나 위치에 새롭게 다시 그려집니다.

예시:
```java
component.getParent().repaint();
```
### 코드예제
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsDrawOvalMouseEx extends JFrame {

    public GraphicsDrawOvalMouseEx() {
        setTitle("마우스 드래깅으로 타원 그리기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicsDrawOvalMouseEx();
    }
    
    // 타원을 그릴 패널 작성. 이 패널에 마우스 리스너 구현
    class MyPanel extends JPanel {
        private Point start = null, end = null;

        public MyPanel() {
            MyMouseListener listener = new MyMouseListener();
            // listener를 아래 두 리스너로 공통으로 등록해야 한다.
            addMouseListener(listener);
            addMouseMotionListener(listener);
        }

        class MyMouseListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
            }

            public void mouseDragged(MouseEvent e) {
                end = e.getPoint();
                repaint(); // 패널의 그리기 요청 주목
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (start == null)
                return;
            g.setColor(Color.BLUE);
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            g.drawOval(x, y, width, height);
        }
    }
}

```
### 코드예제 2
```java
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GraphicsDrawLineMouseEx extends JFrame {

    public GraphicsDrawLineMouseEx() {
        setTitle("마우스로 여러 개의 선 그리기 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicsDrawLineMouseEx();
    }

    class MyPanel extends JPanel {
        private Vector<Point> vStart = new Vector<Point>();
        private Vector<Point> vEnd = new Vector<Point>();

        public MyPanel() {
            MyMouseListener listener = new MyMouseListener();
            addMouseListener(listener);
            addMouseMotionListener(listener);
        }

        class MyMouseListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                vStart.add(e.getPoint());
                vEnd.add(e.getPoint());
                repaint();
            }

            public void mouseDragged(MouseEvent e) {
                int lastIndex = vEnd.size() - 1;
                vEnd.set(lastIndex, e.getPoint());
                repaint();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            for (int i = 0; i < vStart.size(); i++) {
                Point start = vStart.get(i);
                Point end = vEnd.get(i);
                g.drawLine(start.x, start.y, end.x, end.y);
            }
        }
    }
}
```

### 멀티태스킹 ( multi-tasking ) 개념
- 멀티태스킹
    - 여러 개의 작업(태스크)이 동시에 처리되는 것

![예시 이미지](images/123.webp)

![멀티태스킹 프로그램 사례](images/멀티테스킹.webp)

## 스레드와 운영체제

## 스레드(thread)
- 운영체제에 의해 관리되는 하나의 작업 혹은 태스크
- 스레드와 태스크(혹은 작업)은 바꾸어 사용해도 무관

## 멀티스레딩(multi-threading)
- 여러 스레드를 동시에 실행시키는 응용프로그램을 작성하는 기법

### 스레드 구성
- 스레드 코드
  - 작업을 실행하기 위해 작성한 프로그램 코드
  - 개발자가 작성
- 스레드 정보
  - 스레드 명, 스레드 ID, 스레드의 실행 소요 시간, 스레드의 우선 순위 등
  - 운영체제가 스레드에 대해 관리하는 정보

## 멀티태스킹과 멀티스레딩

![예시 이미지](images/스레드.webp)

### 멀티태스킹 구현 기술
- 멀티프로세싱(multi-processing)
  - 하나의 응용프로그램이 여러 개의 프로세스를 생성하고, 각 프로세스가 하나의 작업을 처리하는 기법
  - 각 프로세스 독립된 메모리 영역을 보유하고 실행
  - 프로세스 사이의 문맥 교환에 따른 과도한 오버헤드와 시간 소모의 문제점
- 멀티스레딩(multi-threading)
  - 하나의 응용프로그램이 여러 개의 스레드를 생성하고, 각 스레드가 하나의 작업을 처리하는 기법
  - 하나의 응용프로그램에 속한 스레드는 변수 메모리, 파일 오픈 테이블 등 자원으로 공유하므로, 문맥 교환에 따른 오버헤드가 매우 작음
  - 현재 대부분의 운영체제가 멀티스레딩을 기본으로 함

## 자바 스레드(Thread)와 JVM

### 자바 스레드
- 자바 가상 기계(JVM)에 의해 스케쥴되는 실행 단위의 코드 블럭
- 스레드의 생명 주기는 JVM에 의해 관리됨: JVM은 스레드 단위로 스케쥴링

### JVM과 자바의 멀티스레딩
- 하나의 JVM은 하나의 자바 응용프로그램만 실행
- 자바 응용프로그램이 시작될 때 JVM이 함께 실행됨
  - 자바 응용프로그램이 종료하면 JVM도 함께 종료함
- 응용프로그램은 하나 이상의 스레드로 구성 가능

##Thread 클래스를 상속받아 스레드 만들기

## Thread 클래스를 상속받아 스레드 만들기
- Thread 클래스 상속
- run() 메소드 오버라이딩
  - run() 메소드는 스레드 코드를 포함
  - run() 메소드에서 스레드 실행 시작

## 스레드 객체 생성 및 시작
- 스레드 객체 생성
  - Thread 클래스의 생성자 이용
- 스레드 시작
  - start() 메소드 호출
    - JVM에 의해 스케쥴되기 시작

## Thread 클래스의 주요 메소드
- `Thread()`
  - 스레드 객체 생성
- `Thread(Runnable target)`
  - Runnable 객체를 이용하여 스레드 객체 생성
- `Thread(String name)`
  - 이름이 있는 스레드 객체 생성
- `void run()`
  - 스레드 코드 작성을 위한 메소드
- `void start()`
  - JVM에게 스레드 실행 시작 요청
- `void interrupt()`
  - 스레드 강제 종료
- `static void yield()`
  - 다른 스레드에게 실행 양보
- `void join()`
  - 스레드가 종료할 때까지 대기
- `long getId()`
  - 스레드의 ID값 반환
- `String getName()`
  - 스레드의 이름 반환
- `int getPriority()`
  - 스레드의 우선순위 값 반환
- `void setPriority(int priority)`
  - 스레드의 우선순위 값을 변경
- `Thread.State getState()`
  - 스레드의 상태 값 반환
- `static void sleep(long millis)`
  - 스레드를 지정한 시간만큼 재우기
- `static Thread currentThread()`
  - 현재 실행 중인 스레드 객체의 레퍼런스 반환

### 예제 코드
- Thread를 상속받아 1초 단위로 초 시간을 출력하는
TimerThread 스레드 작성 사례

```java
// TimerThread 클래스 선언
class TimerThread extends Thread {
    // 타이머 값이 출력되는 레이블
    private Label timerLabel;

    // 생성자
    public TimerThread(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    // 스레드 코드 run()이 종료하면 스레드 종료
    @Override
    public void run() {
        // 타이머 카운트 값
        int n = 0;

        while (true) {
            // 타이머 값을 레이블에 설정
            timerLabel.setText(Integer.toString(n));
            n++; // 카운트 증가
            try {
                Thread.sleep(1000); // 1초 동안 잠을 잔 후 실행
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
```

### 예제코드 2
- Thread를 상속받아 1초 단위 타이머 스레드 만들기

```java
// TimerThread를 사용하는 예제
public class ThreadTimerEx extends JFrame {
    public ThreadTimerEx() {
        setTitle("Thread를 상속받은 타이머 스레드 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨테이너 설정
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 타이머 값을 출력할 레이블 생성
        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Gothic", Font.ITALIC, 20));
        c.add(timerLabel);

        // 타이머 스레드 객체 생성
        TimerThread th = new TimerThread(timerLabel);

        setSize(250, 150);
        setVisible(true);

        // 타이머 스레드 시작
        th.start();
    }

    public static void main(String[] args) {
        new ThreadTimerEx();
    }
}
```
### Runnable 인터페이스로 스레드 만들기
```java
// Runnable을 구현하는 새 클래스 작성
class MyRunnable implements Runnable {
    // run() 메소드 구현
    @Override
    public void run() {
        // 스레드 코드 작성
        // 스레드 실행 시작
        System.out.println("스레드 실행 중");

        // 여기에 스레드가 수행할 작업을 작성합니다.
        // 예를 들어, 파일을 읽거나 네트워크 요청을 보낼 수 있습니다.
    }
}
```
![예시 이미지](images/run().png)

```java
// 스레드 객체 생성 및 시작
public class ThreadExample {
    public static void main(String[] args) {
        // 스레드 객체 생성
        MyRunnable myRunnable = new MyRunnable();

        // 스레드 시작
        Thread thread = new Thread(myRunnable);
        thread.start();
        
        // start() 메소드 호출을 통해 스레드가 스케줄되어 JVM에 의해 실행됩니다.
    }
}
```

## Main 스레드
- main 스레드
    - JVM이 응용프로그램을 실행할 때 디폴트로 생성되는 스레드
    - main()메소드
    - main 메소드가 종료하면 main 쓰레드 종료

![예제 12-3](images/Runnable.webp)

### 쓰레드 종료와 타 쓰레드 강제 종료
![설명](images/main스레드.webp)

## 스레드 동기화(Thread Synchronization)

### 멀티스레드 프로그램 작성시 주의점
- 다수의 스레드가 공유 데이터에 동시에 접근하는 경우 공유 데이터의 값에 예상치 못한 결과가 발생할 수 있음.

### 스레드 동기화
- **동기화란?**
    - 스레드 사이의 실행 순서를 제어하고 공유 데이터에 대한 접근을 원활하게 하는 기법.
- 멀티스레드의 공유 데이터의 동시 접근 문제를 해결하기 위한 방법:
    1. 공유 데이터를 접근하는 모든 스레드를 한 줄로 세우기.
    2. 한 스레드가 공유 데이터에 대한 작업을 끝낼 때까지 다른 스레드가 대기하도록 함.

### 자바의 스레드 동기화 방법 - 2가지
1. `synchronized` 키워드로 동기화 블록 지정
2. `wait()`-`notify()` 메소드로 스레드의 실행 순서를 제어함.

## synchronized 블록 지정

### synchronized 키워드
- 스레드가 독점적으로 실행해야 하는 부분(동기화 코드)을 표시하는 키워드.
- 일제 영역(critical section) 표기 키워드.

### synchronized 블록 지정 방법
- 메소드 전체 혹은 코드 블록을 synchronized 블록으로 지정할 수 있음.

### synchronized 블록이 실행될 때
- 먼저 실행한 스레드가 모니터 소유.
    - 모니터: 해당 객체를 독점적으로 사용할 수 있는 권한.
- 모니터를 소유한 스레드가 모니터를 내놓을 때까지 다른 스레드는 대기해야 함.

# 5월 31일 강의

## 스윙 컴포넌트의 이해/정의
스윙 컴포넌트는 자바에서 GUI를 구축하기 위해 제공하는 경량 컴포넌트입니다. 모든 스윙 컴포넌트는 `JComponent` 클래스를 상속받아 다양한 UI 요소를 제공합니다. 이를 통해 개발자는 윈도우, 버튼, 텍스트 필드 등 다양한 인터페이스 요소를 쉽게 구현할 수 있습니다.

## 자바의 GUI 프로그래밍 방법

### 1. 컴포넌트 기반 GUI 프로그래밍
- **스윙 컴포넌트**를 이용하여 쉽게 GUI를 구축
- 자바에서 제공하는 컴포넌트의 한계를 벗어나지 못함

### 2. 그래픽 기반 GUI 프로그래밍
- **그래픽**을 이용하여 GUI 구축
- 개발자가 직접 그래픽으로 화면을 구성하는 부담
- 다양한 GUI를 구축할 수 있는 장점
- GUI의 처리 실행 속도가 빨라, 게임 등에 주로 이용

## 추가 설명
- **사용자 인터페이스 제공**: 그래픽 기반 GUI(Graphical User Interface)는 아이콘, 버튼, 윈도우 등 시각적 요소를 통해 사용자와 컴퓨터가 상호작용할 수 있게 하는 인터페이스입니다.
- **사용자 경험 향상**: GUI는 명령어 기반 인터페이스(CLI)보다 직관적이고 사용하기 쉬워, 사용자 경험을 크게 향상시킵니다. 비전문가도 컴퓨터를 쉽게 사용할 수 있게 합니다.
- **그래픽 요소 활용**: GUI는 그래픽 요소를 활용해 정보를 더 명확하고 이해하기 쉽게 전달합니다. 이를 통해 사용자들은 복잡한 작업도 쉽게 수행할 수 있습니다.
- **운영 체제와 애플리케이션 통합**: GUI는 대부분의 현대 운영 체제와 애플리케이션에서 표준으로 사용되며, 윈도우, 맥OS, 리눅스 등의 시스템에서 다양한 소프트웨어와 함께 작동합니다.

## 컴포넌트 기반 GUI 프로그래밍에서 사용되는 스윙 컴포넌트

### 스윙 컴포넌트의 공통 메소드 - JComponent의 메소드

#### 컴포넌트의 모양과 관련된 메소드
```java
- void setForeground(Color): 전경색 설정
- void setBackground(Color): 배경색 설정
- void setOpaque(boolean): 불투명성 설정
- void setFont(Font): 폰트 설정
- Font getFont(): 폰트 리턴
```

#### 컴포넌트의 위치와 크기에 관련된 메소드
```java
- int getWidth(): 폭 리턴
- int getHeight(): 높이 리턴
- int getX(): x 좌표 리턴
- int getY(): y 좌표 리턴
- Point getLocationOnScreen(): 스크린 좌표상에서의 컴포넌트 좌표 리턴
- void setLocation(int, int): 위치 지정
- void setSize(int, int): 크기 지정
```

#### 컴포넌트의 상태와 관련된 메소드
```java
- void setEnabled(boolean): 컴포넌트 활성화/비활성화 설정
- void setVisible(boolean): 컴포넌트 보이기/숨기기 설정
- boolean isVisible(): 컴포넌트의 보이는 상태 리턴
```

#### 컨테이너를 위한 메소드
```java
- Component add(Component): 자식 컴포넌트 추가
- void remove(Component): 자식 컴포넌트 제거
- void removeAll(): 모든 자식 컴포넌트 제거
- Component[] getComponents(): 자식 컴포넌트 배열 리턴
- Container getParent(): 부모 컨테이너 리턴
- Container getTopLevelAncestor(): 최상위 부모 컨테이너 리턴
```

#### JComponent

1. **스윙 컴포넌트는 모두 상속받는 슈퍼 클래스**: JComponent는 스윙 컴포넌트들이 공통으로 상속받는 `추상 클래스`입니다.
2. **스윙 컴포넌트들이 상속받는 공통 메소드와 상수 구현**: JComponent는 다양한 메소드와 상수를 구현하여 스윙 컴포넌트들이 이를 활용할 수 있게 합니다.
3. **JComponent의 주요 메소드 사례**:
   - `setBackground(Color bg)`: 컴포넌트의 배경색을 설정합니다.
   - `setForeground(Color fg)`: 컴포넌트의 전경색(텍스트 색상 등)을 설정합니다.
   - `setFont(Font f)`: 컴포넌트의 폰트를 설정합니다.
   - `repaint()`: 컴포넌트를 다시 그립니다.
   - `addMouseListener(MouseListener l)`: 마우스 이벤트를 감지하기 위해 리스너를 추가합니다.

### 실제활용 예제 코드
```java
import javax.swing.*;
import java.awt.*;

public class CustomComponent extends JComponent {
    
    // 이 메서드는 컴포넌트를 그릴 때 호출됩니다.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 부모 클래스의 기본 페인팅 작업 수행
        g.setColor(Color.BLUE); // 색상을 파란색으로 설정
        g.fillRect(10, 10, 100, 100); // (10, 10) 위치에 100x100 크기의 파란색 사각형 그리기
    }

    // 메인 메서드: 애플리케이션 실행 진입점
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom JComponent"); // 새로운 프레임 생성
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 클릭 시 애플리케이션 종료
        frame.add(new CustomComponent()); // 커스텀 컴포넌트를 프레임에 추가
        frame.setSize(200, 200); // 프레임 크기를 200x200으로 설정
        frame.setVisible(true); // 프레임을 화면에 표시
    }
}
```

## 레이블 생성 예
1. **문자열 레이블 생성**
```java
JLabel textLabel = new JLabel("사랑합니다");
```

## 이미지 레이블 생성
1. **이미지 파일로부터 이미지를 읽기 위해 Imageicon 클래스 사용**
2. **다를 수 있는 이미지 : png , gif , jpg**
```java
--> sunset.jpg의 경로명이 "images/sumset.jpg"인 경우
```

## 수평 정령 값을 가진 레이블 컴포넌트 생성

1. **수평 정렬로, 문자열과 이미지를 모두 가진 레이블**
```java
Imageicon image = new Imageicon("images/sunset.jpg");
JLabel textLabel = new JLabel("사랑합니다"), image, SwingConstants CENTER");
```

## 이미지 버튼 만들기

### 버튼에 3개의 이미지 등록하기

- 하나의 버튼에 3개의 이미지를 등록합니다.
- 마우스 조작에 따라 3개의 이미지 중 적절한 이미지가 자동으로 출력됩니다.

### 3개의 버튼 이미지

- **normallcon**
  - 버튼의 보통 상태(디폴트) 때 출력되는 이미지
  - 생성자에 이미지 아이콘을 전달하거나 `JButton`의 `setIcon(normalIcon)` 메소드를 사용합니다.
  
- **rolloverlcon**
  - 버튼에 마우스가 올라갈 때 출력되는 이미지
  - 이미지 설정 메소드: `JButton`의 `setRolloverIcon(rolloverIcon)`을 사용합니다.

- **pressedicon**
  - 버튼을 누른 상태 때 출력되는 이미지
  - 이미지 설정 메소드: `JButton`의 `setPressedIcon(pressedIcon)`을 사용합니다.

### 이미지 설정

#### 이미지 로딩

1. **필요한 이미지를 로딩합니다. 예시:**
```java
ImageIcon normalIcon = new ImageIcon("images/normalIcon.gif");
ImageIcon rolloverIcon = new ImageIcon("images/rolloverIcon.gif");
ImageIcon pressedIcon = new ImageIcon("images/pressedIcon.gif");
```

2. **버튼에 이미지 등록**
- JButton의 메소드를 호출하여 이미지를 등록합니다. 예시:
```java
JButton button = new JButton("테스트버튼", normalIcon); // normalIcon 설정
button.setRolloverIcon(rolloverIcon);
button.setPressedIcon(pressedIcon);
```

3. **실행 중에 이미지 변경**
- 실행 중에는 다른 이미지로 변경할 수 있습니다. 예시:
```java
// rolloverIcon으로 변경
// pressedIcon으로 변경
ImageIcon newIcon = new ImageIcon("images/newIcon.gif");
button.setIcon(newIcon); // 디폴트 이미지 변경
```

## 체크박스의 Item 이벤트 처리

### Item 이벤트

- 체크박스의 선택 상태에 변화가 생길 때 발생하는 이벤트입니다. 이는 사용자가 마우스나 키보드로 체크박스를 선택하거나 해제하거나, 프로그램에서 체크박스를 선택하거나 해제하여 체크 상태에 변화가 생길 때 발생합니다.

### 체크박스 설정

```java
JCheckBox checkbox = new JCheckBox("A");
checkbox.setSelected(true); // 선택 상태로 변경
```

### 이벤트 처리

- Item 이벤트가 발생하면 `ItemEvent` 객체가 생성됩니다.
- `ItemListener` 리스너를 이용하여 이벤트를 처리합니다.

#### ItemListener의 추상 메소드

- `void itemStateChanged(ItemEvent e)`: 체크박스의 선택 상태가 변하는 경우 호출됩니다.

#### ItemEvent의 주요 메소드

- `int getStateChange()`: 체크박스가 선택된 경우 `ItemEvent.SELECTED`, 해제된 경우 `ItemEvent.DESELECTED`를 리턴합니다.
- `Object getItem()`: 이벤트를 발생시킨 아이템 객체를 리턴합니다. 체크박스의 경우 `JCheckBox` 컴포넌트의 레퍼런스를 리턴합니다.


```java
checkbox.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 체크박스가 선택된 경우 처리
            System.out.println("체크박스가 선택되었습니다.");
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // 체크박스가 해제된 경우 처리
            System.out.println("체크박스가 해제되었습니다.");
        }
    }
});
```
## JRadioButton으로 라디오버튼 만들기

### JRadioButton의 용도

JRadioButton은 버튼 그룹을 형성하고, 그룹에 속한 버튼 중 하나만 선택되는 라디오버튼입니다.

#### 체크박스와의 차이점

- 체크박스는 각각 선택 또는 해제가 가능하지만,
- 라디오버튼은 그룹에 속한 버튼 중 하나만 선택됩니다.

### 라디오버튼 생성

다양한 생성자를 사용하여 라디오버튼을 생성할 수 있습니다:

- `JRadioButton()`: 텍스트 없이 기본 라디오버튼을 생성합니다.
- `JRadioButton(Icon icon)`: 아이콘 이미지가 있는 라디오버튼을 생성합니다.
- `JRadioButton(Icon icon, boolean selected)`: 아이콘 이미지가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.

- `JRadioButton(String text)`: 텍스트가 있는 라디오버튼을 생성합니다.
- `JRadioButton(String text, boolean selected)`: 텍스트가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.
- `JRadioButton(String text, Icon icon)`: 텍스트와 아이콘 이미지가 있는 라디오버튼을 생성합니다.
- `JRadioButton(String text, Icon icon, boolean selected)`: 텍스트와 아이콘 이미지가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.

### 라디오버튼 생성 및 Item 이벤트 처리

#### 버튼 그룹과 라디오버튼 생성 과정

```java
ButtonGroup group = new ButtonGroup();
JRadioButton apple = new JRadioButton("Apple");
JRadioButton pear = new JRadioButton("Pear");
JRadioButton cherry = new JRadioButton("Cherry");

group.add(apple);
group.add(pear);
group.add(cherry);

container.add(apple);
container.add(pear);
container.add(cherry);
```

### 라디오버튼에 Item 이벤트 처리
라디오버튼의 선택 상태 변경을 감지하기 위해 ItemListener를 사용합니다. 이벤트가 발생하면 setSelected()를 호출하여 선택 상태를 변경할 수 있습니다.

```java
apple.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 라디오버튼이 선택되었을 때 처리
        }
    }
});
```
## JTextField로 한 줄 입력 창 만들기

JTextField는 한 줄의 문자열을 입력 받는 창, 즉 텍스트필드를 나타냅니다. 사용자가 텍스트를 입력하는 도중 <Enter> 키가 입력되면 Action 이벤트가 발생합니다. 입력 가능한 문자 개수와 입력 창의 크기는 서로 다를 수 있습니다.

### 텍스트필드 생성

다양한 생성자를 사용하여 텍스트필드를 생성할 수 있습니다:

- `JTextField()`: 빈 텍스트필드를 생성합니다.
- `JTextField(int cols)`: 입력 열의 갯수가 cols개인 텍스트필드를 생성합니다.
- `JTextField(String text)`: 초기 텍스트로 문자열 text를 갖는 텍스트필드를 생성합니다.
- `JTextField(String text, int cols)`: 입력 열의 수는 cols이고 초기 텍스트로 문자열 text를 갖는 텍스트필드를 생성합니다.

#### 초기값이 "컴퓨터공학과"인 텍스트필드 생성 예
```java
JTextField tf2 = new JTextField("컴퓨터공학과");
```

## JList<E>

JList는 하나 이상의 아이템을 보여주고 사용자가 아이템을 선택할 수 있도록 하는 리스트입니다. Java 7부터는 제네릭 리스트로 변경되어 `<E>`에 지정된 타입의 객체만 저장할 수 있습니다. 

JList를 JScrollPane에 삽입하여 스크롤이 가능하도록 만들 수 있습니다.

### 리스트 생성

다양한 생성자를 사용하여 리스트를 생성할 수 있습니다:

- `JList()`: 빈 리스트를 생성합니다.
- `JList<E>(List<E> listData)`: 리스트에 아이템을 공급받는 리스트를 생성합니다.
- `JList<E>(E[] listData)`: 리스트에 아이템을 배열로부터 공급받는 리스트를 생성합니다.

#### 예시: 9개의 과일 이름 문자열이 든 리스트 만들기

```java
String[] fruits = {"apple", "banana", "kiwi", "mango", "pear", "peach", "berry", "strawberry", "blackberry"};
JList<String> strList = new JList<String>(fruits);
```
## 메뉴 구성

### **메뉴 만들기에 필요한 스윙 컴포넌트는 다음과 같습니다:**

- **메뉴아이템 (MenuItems)**: 여러 개의 메뉴 아이템을 가지는 항목입니다.
- **메뉴 (Menus)**: 여러 개의 메뉴 아이템을 가지는 그룹입니다.
- **메뉴바 (JMenuBar)**: 여러 개의 메뉴를 붙이는 바이며, 프레임에 부착됩니다.
- **분리선 (Separator)**: 메뉴 아이템 사이의 분리선으로, separator라고도 합니다. Menu 클래스의 `addSeparator()` 메소드를 호출하여 삽입할 수 있습니다.

### **메뉴 만드는 과정**

1. **JMenuBar 컴포넌트 생성**: 메뉴바를 생성합니다.
2. **JMenu 컴포넌트를 생성하여 JMenuBar에 붙임**: 메뉴를 생성하고 메뉴바에 추가합니다.
3. **JMenuItem 컴포넌트를 생성하여 JMenu에 붙임**: 각 메뉴에 메뉴 아이템을 추가합니다. 여러 개의 메뉴와 메뉴 아이템을 생성할 수 있습니다.
4. **JMenuBar 컴포넌트를 JFrame에 붙임**: 생성한 메뉴바를 JFrame에 추가합니다.

```java
JMenuBar mb = new JMenuBar(); // 1. JMenuBar 생성
JMenu screenMenu = new JMenu("Screen"); // 2. JMenu 생성
mb.add(screenMenu); // 2. JMenu를 JMenuBar에 추가
screenMenu.add(new JMenuItem("Load")); // 3. JMenuItem 추가
screenMenu.add(new JMenuItem("Hide"));
screenMenu.add(new JMenuItem("ReShow"));
screenMenu.addSeparator();
screenMenu.add(new JMenuItem("EXIT"));
frame.setMenuBar(mb); // 4. JMenuBar를 JFrame에 추가
```

### 메뉴 아이템에 Action 리스너 설정
```java
JMenuItem item = new JMenuItem("Load");
item.addActionListener(new MenuActionListener());
screenMenu.add(item);

// Action 이벤트를 처리할 리스너 작성
class MenuActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // 사용자가 Load 메뉴 아이템을 선택하는 경우 처리할 작업을 구현합니다.
    }
}
```

## 팝업 다이얼로그, JOptionPane

### 팝업 다이얼로그
- 사용자에게 메시지를 전달하거나 문자열을 간단히 입력받는 용도
- JOptionPane 클래스를 이용하여 생성
  - static 타입의 간단한 메소드 이용

### 입력 다이얼로그 - `JOptionPane.showInputDialog()`
- 한 줄을 입력 받는 다이얼로그
- `static String JOptionPane.showInputDialog(String msg)`
  - `msg`: 다이얼로그 메시지
  - `리턴 값`: 사용자가 입력한 문자열, 취소 버튼이 선택되거나 창이 닫히면 null 리턴

```java
String name = JOptionPane.showInputDialog("이름을 입력하세요");
// name: 사용자가 입력한 값 (예: "Java Kim")
```
### 확인 다이얼로그 - `CO-JOptionPane.showConfirmDialog() `
- 사용자로부터 Yes/No 응답을 입력 받는 다이얼로그

```java
static int 30ptionPane.showConfirDialog(Component parentComponent, Object asg, String title, int optionType)
// parentComponent: ClO DESEN
```

# 5월 24일 강의

## 이벤트 기반 프로그래밍

이벤트의 발생에 의해 프로그램 흐름이 결정되는 방식:

1. **이벤트 발생 시**: 이벤트를 처리하는 `루틴`이 실행됩니다.
2. **실행 코드 결정**: 실행될 코드는 이벤트의 발생에 의해 전적으로 결정됩니다.

### 반대 개념: 배치 실행

- **배치 실행**: 프로그램의 흐름을 개발자가 결정하는 방식입니다.

### 이벤트의 종류

1. **사용자 입력**:
   - `마우스 드래그`
   - `마우스 클릭`
   - `키보드 누름`
2. **센서 입력**:
   - 네트워크 데이터 송수신
3. **메시지**:
   - 다른 응용프로그램 또는 스레드로부터의 메시지

### 이벤트 기반 응용 프로그램의 구조

- 각 이벤트마다 처리하는 `리스너 코드`를 보유합니다.

### GUI 응용프로그램

- GUI 응용프로그램은 이벤트 기반 프로그래밍으로 작성됩니다.
- **GUI 라이브러리의 종류**:
  - `C++`
  - `Windows`
  - `Android`
  - `Visual Basic` 등

## 자바 스윙 프로그램에서 이벤트 처리 과정 ver.코드

**예시: 자바 스윙 프로그램에서의 이벤트 처리**

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventDrivenExample {
    private JFrame frame;
    private JButton button;
    private JLabel label;

    public EventDrivenExample() {
        frame = new JFrame("이벤트 기반 프로그래밍 예제");
        button = new JButton("클릭하세요");
        label = new JLabel("버튼을 클릭하세요.");

        // 버튼에 이벤트 리스너 등록
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("버튼이 클릭되었습니다.");
            }
        });

        // 프레임 구성
        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventDrivenExample example = new EventDrivenExample();
    }
}
```

### 이벤트 처리 과정

1. **이벤트 발생**:
   - 예: 마우스 움직임, 키보드 입력

2. **이벤트 객체 생성**:
   - 현재 발생한 이벤트에 대한 정보를 가진 객체를 생성합니다.

3. **이벤트 리스너 찾기**:
   - 응용프로그램에서 작성된 이벤트 리스너를 찾습니다.

4. **이벤트 리스너 실행**:
   - 리스너에 이벤트 객체를 전달하고, 리스너 코드를 실행합니다.

## 이벤트 객체

1. **개요**
    - 발생한 이벤트에 관한 정보를 가진 객체입니다. 이벤트 리스너에 전달됩니다.

2. **이벤트 객체가 포함하는 정보**
    - 이벤트 종류와 이벤트 소스
    - 이벤트 발생 좌표
    - 버튼이나 메뉴 아이템 문자열
    - 마우스 버튼 번호 및 클릭 횟수
    - 키의 코드 값과 문자 값
    - 컴포넌트의 체크 상태

3. **이벤트 소스를 알아내는 메소드**
    1. **명시적 지정**
        - 이벤트 핸들러 등록 시 소스를 명시적으로 지정합니다.
    2. **이벤트 객체 활용**
        - 이벤트 핸들러의 인자로 전달되는 이벤트 객체를 활용하여 소스를 식별합니다.
    3. **DOM 탐색**
        - 이벤트 핸들러가 등록된 요소를 DOM 탐색을 통해 찾습니다.
    4. **이벤트 델리게이션**
        - 상위 요소에서 이벤트를 캐치하고 실제 소스를 파악합니다.

## 이벤트 리스너

1. **개요**
    - 이벤트를 처리하는 자바 프로그램 코드 클래스로 작성됩니다.

2. **자바의 리스너 인터페이스**
    - 다양한 리스너 인터페이스가 제공됩니다.
      -  예: 버튼 클릭 이벤트를 처리하기 위한 인터페이스 등

3. **사용자의 이벤트 리스너 작성**
    - 자바의 리스너 인터페이스를 상속받아 구현합니다.
    - 리스너 인터페이스의 모든 추상 메소드를 구현합니다.

4. **이벤트 리스너 작성 과정 사례**
    - **이벤트와 이벤트 리스너 선택**
        - `버튼 클릭`을 처리하고자 하는 경우
        - 이벤트: `Action 이벤트`
        - 이벤트 리스너: `Action 리스너`

**이벤트 리스너 클래스 작성: Action 리스너 인터페이스 구현**
```java
import java.util.function.*;

// 액션 리스너 인터페이스
interface ActionListener {
    void onActionPerformed();
}

// 메인 클래스
public class Main {
    public static void main(String[] args) {
        // 액션 리스너 인터페이스 구현
        ActionListener listener = () -> System.out.println("액션이 수행되었습니다!");

        // 액션 발생
        listener.onActionPerformed();
    }
}
```

### 이벤트 리스너 작성 방법
1. **독립 클래스로 작성**
   - 이 방법은 이벤트 리스너를 `독립적인 클래스`로 작성, 이벤트 리스너 클래스는 보통 `특정 이벤트를 처리하기 위해 설계`
```java
public class MyActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // 이벤트 처리 로직 작성
    }
}
```

2. **이벤트 리스너를 완전한 클래스로 작성**
   - 이 방법은 이벤트 리스너를 인터페이스로 구현하는 대신, `클래스 내`에 `메소드로 작성`
```java
public class MyButton {
    public void onClick() {
        // 이벤트 처리 로직 작성
    }
}

public class MyActionListener implements ActionListener {
    private MyButton button;

    public MyActionListener(MyButton button) {
        this.button = button;
    }

    public void actionPerformed(ActionEvent e) {
        button.onClick();
    }
}
```

3. **이벤트 리스너를 여러 곳에서 사용할 때 적합**
   - 여러 곳에서 사용될 수 있는 `일반적인 이벤트 리스너`를 작성할 때는, `인터페이스를 구현`하거나 `익명 클래스`로 작성
```java
public class MyActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // 이벤트 처리 로직 작성
    }
}
```
## 육합 클래스로 작성
```java
public class MyButton {
    public void onClick() {
        // 이벤트 처리 로직 작성
    }
}

public class MyActionListener implements ActionListener {
    private MyButton button;

    public MyActionListener(MyButton button) {
        this.button = button;
    }

    public void actionPerformed(ActionEvent e) {
        button.onClick();
    }
}
```

### 이벤트 리스너를 완전한 클래스로 작성
- 이벤트 리스너를 `별도의 클래스로 작성`하여 구현, 이 클래스는 해당 이벤트에 대한 `모든 처리 로직`을 포함

### 이벤트 리스너를 여러 곳에서 사용할 때 작성
- 이벤트 리스너를 여러 곳에서 사용할 때에는 `하나의 완전한 클래스`로 작성을 공유

## 내부 클래스로 작성
```java
public class MyComponent {
    public void addClickListener() {
        class MyActionListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                // 이벤트 처리 로직 작성
            }
        }
        // 리스너를 컴포넌트에 추가
    }
}
```
### 클래스 안에 맴버처럼 클래스 작성
- 이벤트 리스너를 `외부 클래스` 내부에 선언하여 작성, `해당 클래스 내부`에서만 사용

### 이벤트 리스너를 특정 클래스에서만 사용할 때 적합
- 특정 클래스에서만 사용되는 이벤트 리스너를 `내부 클래스`로 작성해 `캡슐화`

## 익명 클래스
```java
// JButton button = new JButton("Click me");
// button.addActionListener(new ActionListener() {
//     public void actionPerformed(ActionEvent e) {
//         // 이벤트 처리 로직 작성
//     }
// });
```

### 클래스의 이름 없이 간단한 리스너 작성
- `클래스 이름 없이 짧은 코드`로 간단한 이벤트 리스너를 작성, `주로 간단한 이벤트 처리`에 사용

### 클래스 조차 만들 필요 없이 리스너 코드가 간단한 경우에 적합
- `클래스 선언 없이 짧은 코드`로 리스너를 작성해, `단순한 이벤트 처리`에 유용

## 익명 클래스로 이벤트 리스너 작성
```java
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // JButton button = new JButton("Click me");
        // 익명 클래스로 ActionListener를 구현하여 이벤트 리스너 작성
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 버튼 클릭 시 실행되는 이벤트 처리 로직 작성
            }
        });
    }
}
```
`위 코드에서는 JButton의 addActionListener 메소드를 호출하여 익명 클래스를 이용해 ActionListener를 구현하고, actionPerformed 메소드 내에 버튼 클릭 시 실행될 이벤트 처리 로직을 작성함 `

### 익명 클래스 : 이름이 없는 클래스
- 익명 클래스를 사용하여 이벤트 리스너를 작성, `한 곳에서만 사용`되는 간단한 이벤트 처리에 적합

## 간단한 리스너의 경우 익명 클래스 사용 추천
- 리스너가 매우 간단한 경우 익명 클래스를 사용하여 `코드를 간결하게 유지`, 메소드의 개수가 적은 경우에 사용

## Action리스너를 구현하는 익명의 이벤트 리스너 작성
- 이름을 가진 클래스를 작성하지 않고도 익명 클래스를 사용하여 간단한 이벤트 리스너를 작성, `단순한 이벤트 처리`에 적합

## 어댑터 클래스
```java
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {
    // 클릭 이벤트 처리
    public void mouseClicked(MouseEvent e) {
        // 클릭 이벤트 처리 로직 작성
    }
}

```
`위 코드는 MouseAdapter 클래스를 이용해서 간단하게 마우스 이벤트를 처리하는 예제 :  MyMouseListener 클래스를 만들어서 MouseAdapter를 상속받아 클릭 이벤트 처리하는 메소드를 오버라이드 하고, 필요한 이벤트에만 집중해서 코드를 간결하게 작성`

### 이벤트 리스너 구현에 따른 부담
- 리스너의 추상 메소드를 모두 구현해야하는 부담
- 예: 마우스 리스너에서 마우스가 눌러지는 경우만 처리하고자 하는 경우에도 나머지 4개의 메소드를 모두 구현하는 부담

### 어댑터 클래스
- 리스너의 모든 메소드를 단순 리턴하도록 만든 클래스
- 마우스 어댑터 사용 예시

## 키 이벤트와 포커스

**키 이벤트 발생 시점**
- 키를 누르는 순간
- 누른 키를 떼는 순간
- Unicode 키의 경우, 누른 키를 떼는 순간

**키 이벤트를 받을 수 있는 조건**
- `모든 컴포넌트`
- 현재 포커스를 가진 컴포넌트가 키 이벤트를 독점

**포커스**
- 컴포넌트나 응용프로그램이 키 이벤트를 `독점하는 권한`

**유니코드 키의 특징**
1. `국제 산업 표준`
2. 전 세계의 문자를 컴퓨터에서 `일관되게 표현`하기 위한 `코드 체계`
3. 문자들에 대해서만 `키 모드 값 정의`

**가상 키와 입력된 키 판별**

*key*이벤트 객체
1. 입력된 키 정보를 가진 이벤트 객체
2. *key*이벤트 객체의 메소드로 입려된 키 판별

*key*이벤트 객체의 메소드로 입렫된 키 판별
1. 키의 `유니코드 문자 값 리턴`
2. `유니코드 문자 키인 경우`에만 의미 있음
3. 입력된 키를 판별하기 위해 `문자 값과 비교`하면 됨

*int key*이벤트 
1. 유니코드 키 포함
2. 모든 키에 대한 `정수형 키 코드 리턴`
3. 입력된 키를 `판별`하기 위해
4. 가상키 값과 `비교`하기 위해

**가상 키**
1. 가상키는 *key*이벤트 클래스에 `상수로 선언`
2. 설명 : *key*Event에서의 가상 키는 사용자 입력 장치에 실제로 `존재하지 않지만`, 프로그래밍에서 논리적으로 사용되는 `키`를 말합니다. 이는 주로 특수 키나 기능 키 등을 포함하며, 프로그램에서 `이벤트 핸들링을 위해 활용`됩니다. 사용자의 키보드나 마우스 입력을 프로그램이 인식하고, 그에 따른 동작을 수행할 때 주로 사용됩니다.


# 5월 17일 강의 

배치 관리자 대표 유형 4가지

1. flowlayout 배치관리자

    <1>컴포넌트가 삽입되는 순서대로 왼쪽에서 오른쪽으로 배치

    <2>배치할 공간이 없으면 아래로 내려와서 반복한다

2. borderlayout 배치관리자

    <1>컨테이너의 공간을 동,서,남,북,중앙의 5개의 영역을 나눔

3. gridlayout 관리자

    <1>컨테이너를 프로그램에서 설정한 동일한 크기의 2차원 격차로 나눔

    <2>컴포넌트는 삽입 순서대로 좌에서 우로 다시 위에서 아래로 배치

4. cardlayout 관리자

    <1>컨테이너의 공간에 카드를 쌓아 놓은 듯이 컴포넌트를 포개어 배치

컨테이너의 디폴트 배치관리자
1. 컨테이너 생성시 자동으로 생성되는 배치관리자


컨테이너의 새로운 배치관리자 설정

1. 컨테이너에 새로운 배치관리자 설정

    <1>setlayout 메소드 호출
    
        --> JPanel 컨테이너에 borderlayout 배치관리자를 설정하는 예 .

flowlayout 배치관리자
1. 배치방법
    <1>컴포넌트를 컨테이너 생성 위로 생성함

flowlayout의 생성자
1. 생성자
    <1>flowlayout()
    <2>flowlayout(int, hGap, int vGap)
        --> align 컴포넌트를 정렬하는 방법 지정
        --> hGap 좌우 두 컴포넌트 사이의 수평 간격 픽셀 단위:디폴트는 5
        --> vGap 상하 두 컴포넌트 사이의 수평 간격 픽셀 단위:디폴트는 5

borderlayout 배치관리자
1. 배치방법
    <1>컨테이너 공간을 5 구역으로 분할, 배치
        --> 동, 서, 남, 북

2. 배치종류
    <1>add( component comp, int dex )

borderlayout 생성자와 add()메소드
1. 생성자
    <1>borderlayout()
    <2>borderlayout(int, hGap, int vGap)
        --> hGap 좌우 두 컴포넌트 사이의 수평 간격 픽셀 단위:디폴트는 0
        --> vGap 상하 두 컴포넌트 사이의 수평 간격 픽셀 단위:디폴트는 0

2. add()메소드
    <1>void add (component comp, int dex )
    <2>comp 컴포넌트를 index위치에 삽입한다
    <3>index : 컴포넌트의 위치

girdlayout 배치관리자
1. 배치방법
    <1>컨테이너 공간을 동일한 사각형 격자(그리드)로 분할하고 각 셀에 컴포넌트를 하나씩 배치
        --> 생성자에 행수와 열수 지정
        --> 셀에 왼쪽에서 오른쪽으로, 다시 위에서 아래로 순서대로 배치

girdlayout 생성자
1. girdlayout()
2. girdlayout( int rows, int cols )
3. girdlayout( int rows, int hGap, int vGap)
        --> rows: 격자의 행수: 디폴트 1
        --> cols: 격자의 열수: 디폴트 1
        --> hGap: 좌우 두 컴포턴트 사이의 수평 간격, 픽셀단위: 디폴트 0
        --> vGap: 상하 두 컴포턴트 사이의 수평 간격, 픽셀단위: 디폴트 0
        --> rows x cols 만큼의 셀을 가진 격자로 컨테이너 공간을 분할, 배치



# 4월 19일 강의

추상 클래스
1. 추상 메소드
    <1>abstract로 선언된 메소드, 메소드의 코드는 없고 원형만 선언

2. 추상 클래스
    <1>추상 메소드를 가지며, abstract로 선언된 클래스
    <2>추상 메소드 없이, abstract로 선언된 클래스

추상 클래스의 상속과 구현
1. 추상 클래스 상속
    <1>추상 클래스를 상속받으면 추상 클래삭 됨
    <2>서브 클래스도 abstract로 선언해야 함

2. 추상 클래스 구현
    <1>서브 클래스에서 슈퍼 클래스와 추상 메소드 구현(오버라이딩)
    <2>추상 클래스로 구현한 서브 클래스는 추상 클래스 아님

추상 클래스의 목적
1. 추상 클래스의 목적
    <1>상속을 위한 슈퍼 클래스로 활용하는 것
    <2>서브 클래스에서 추상 메소드 구현
    <3>다향성 실현

[ 인터페이스의 필요성?? ]
추상 클래스는 일부 메서드를 구현하지만 완전한 구현을 제공하지 않습니다. 이때 인터페이스를 함께 사용하면 다중 상속과 유연성을 제공할 수 있습니다. 추상 클래스는 공통 기능을 제공하고 인터페이스는 다양한 기능을 구현할 수 있어서 코드의 재사용성과 확장성을 높입니다. 또한, 인터페이스를 이용하여 코드의 의도를 명확히 표현하고 결합도를 낮출 수 있습니다.

자바의 인터페이스
1. java
    <1>클래스가 후현해야 할 메소드들이 선언되는 추상형
    <2>인터페이스 선언
        --> interface 키워드로 선언

2. 자바 7이전까지
    <1>인터페이스는 상수와 추상 메소드로만 구성

3. 자바 8부터
    <1>상수와 추상메소드 포함
    <2>defalut 메소드 포함
    <3>private 메소드 포함

4. 여전히 인터페이스에는 필드(맴버 변수) 선언불가

자바 인터페이스의 특징
1. 인터페이스의 객체 생성 불가
2. 인터페이스 타비의 레퍼런스 변수 선언 가능

인터페이스의 구성 요소들의 특징
1. 메서드 시그니처(Method Signature): 메서드의 이름, 매개변수 목록, 반환 유형으로 구성되고, 메서드의 내용은 없으며 단지 정의만 포함

2. 상수(Constant): 상수는 인터페이스 내에서 선언되는 변수로, 값을 변경할 수 없는 고정된 값, 주로 관련된 상수들을 그룹화하여 사용

3. 디폴트 메서드(Default Method): Java 8부터 도입된 기능으로, 인터페이스에 메서드의 구현을 제공하고, 구현된 메서드를 갖게 됨으로써, 해당 인터페이스를 구현한 클래스에서 디폴트 메서드를 오버라이딩 안함

4. 정적 메서드(Static Method): Java 8부터 지원되며, 정적 메서드는 인터페이스에 속하면서도 인스턴스를 생성하지 않고도 호출할 수 있는 메서드, 주로 도우미 함수나 유틸리티 메서드를 제공하는 데 사용

5. 추상 메서드(Abstract Method): 메서드의 정의만 있고 구현이 없는 메서드, 이러한 메서드들은 인터페이스를 구현하는 클래스에서 반드시 구현되어야 함

인터페이스 상속/구현
1. 인터페이스의 추상 메소드를 모두 구현한 클래스 작성
    <1>implements 키워드 사용
    <2>여러개의 인터페이스 사용 가능

자바의 패키지와 묘듈
1. 패키지
    <1>서로 관련된 클래스와 인터페이스를 컴파일한 클래스 파일들을 묶어 놓은 디렉터리
    <2>하나의 응용프로그램은 한개 으시아의 패키지로 작성
    <3>패키지는 .jar로 압축할 수 있음 

2. 모듈
    <1>여러 패키지와 이미지 등의 자원을 모아 놓은 컨테이너
    <2>하나의 모듈을 하나의 .jmod 파일에 저장

3. java 9부터 모듈화 도입
    <1>플랫폼의 모듈화
        --> Java 9부터 도입된 모듈화 시스템은 응용 프로그램, 라이브러리 및 JDK를 모듈로 구성하여 캡슐화, 명시적 의존성, 가시성을 제공하고, 이를 통해 의존성 관리가 용이해지고 라이브러리 충돌을 방지하며 자원을 효율적으로 활용가능

자바의 모듈화의 목적
1. 모듈화의 목적
    <1>java 9 부터 자바 api를 여러 모듈 (99)로 분할
        --> java 8이전 까지는 한 파일에 모든 api저장

2. 응용프로그램이 실행할 때, 꼭 필요한 모듈들로만 실행 환경 구축
    <1>메모리 자원이 열악한 작은 소형기기에 꼭 필요한 모듈로 구성된 작은 크기의 실행 이미지를 만들기 위함

모듈의 현실
1. java 9부터 전면적을 도입
2. 복잡한 개념
3. 큰 자바 응용프래그램에는 개발, 유지보수등에 적합
4. 현실적으로 모듈로 나누어 자바 프로그램을 작성할 필요 없음

자바 api의 모듈 파일들
1. 자바 JDK가 제공하는 모듈 파일들
    <1>자바가 설치된 jmod 디렉터리에 모듈 파일 존재
        --> .jmod 확장자를 가진 파일
        --> 모듈은 수십개
        --> 모듈 파일은 zip 포맷으로 압축된 파일
    <2>모듈 파일에는 자바api 패키지와 클래스들이 들어 있음
    <3>jmod 명령을 이용하여 모듈 파일에 들어 있는 패키지를 풀어 낼 수 있음

패키지 사용하기, import문
1. 다른 패키지에 작성된 클래스 사용
    <1>import를 이용하지 않는 경우
        --> 소스에 클래스 이름이 완전 경로명 사용

2. 필요한 클래스만 import
    <1>소스 시각 부분에 클래스의 경로명 import
    <2>import 패키지, 클래스
    <3>소스에는 클래스 명만 명시하면 됨

3. 패키지 전체를 import
    <1>소스 시각 부분에 패키지의 경로명 import
    <2>import 패키지.*
    <3>import.java.util.*

패키지 만들기
1. 클래스 파일이 저장되는 위치는?
    <1>클래스나 인터페이스가 컴파일되면 클래스 파일( .class ) 생성
    <2>클래스 파일은 패키지로 선언된 디렉터리 저징

2. 패키지 선언
    <1>소스 파일의 맨 앞에 컴파일 후 저장될 패키지 지정

디폴트 패키지
1. 명시적인 패키지 선언 없음: 디폴트 패키지는 이름이 없으며 패키지 선언이 필요하지 않음, 클래스를 디폴트 패키지에 정의할 때는 패키지 이름을 지정하지 않음

2. 접근 제어의 제한: 디폴트 패키지에 속한 클래스는 해당 패키지 외부에서 접근할 수 없음, 이는 다른 패키지에 속한 클래스들처럼 패키지 외부로 공개되지 않음을 의미함

3. 관리의 어려움: 클래스들이 모두 한 곳에 모여 있어서 관리가 어려울 수 있음, 패키지를 사용하여 클래스를 카테고리화하고 구조화함으로써 코드의 가독성과 유지 보수성을 향상이 가능

자바 모듈화의 목적 
1. 가장 큰 목적
    <1>자바 컴포넌트들으ㅡㄹ 필요에 따라 조립하여 사용하기 위함
    <2>컴퓨터 시스템의 불필요한 부담 감소
        --> 세밀한 모듈화를 통해 필요 없는 모듈이 로드되지 않게 함

JDK의 주요 패키지
1. ㅓava.lang: 자바 프로그래밍의 기본적인 클래스와 인터페이스를 제공합니다. 여기에는 기본 데이터 타입 (예: Integer, Double), 스레드, 예외 처리 등을 다루는 클래스들이 포함됩니다.

2. java.util: 자료 구조 (예: 리스트, 맵, 큐 등)와 관련된 클래스 및 유틸리티를 제공합니다. 이 패키지에는 날짜 및 시간 관련 클래스, 랜덤 숫자 생성기, 정렬 알고리즘 등이 포함됩니다.

3. java.io: 입력 및 출력 작업을 수행하기 위한 클래스를 제공합니다. 파일 읽기/쓰기, 네트워크 통신 등을 처리하는 클래스들이 여기에 포함됩니다.

객체 속성
1. object 클래스는 객체의 속성을 나타내는 메소드 제공

2. Object 클래스 설명(ver. chatGPT)

객체의 속성을 나타내는 메소드로는 toString(), hashCode(), equals(), getClass() 등이 있습니다.
이러한 메소드들은 모든 자바 클래스에서 사용할 수 있으며, 필요에 따라 오버라이딩하여 해당 클래스의 속성에 맞게 동작을 재정의할 수 있습니다.
toString() 메소드는 객체를 문자열로 표현하는 데 사용되며, hashCode() 메소드는 객체의 해시 코드를 반환합니다.
equals() 메소드는 객체 간의 동등성을 비교하고, getClass() 메소드는 객체의 클래스를 반환합니다.

toString() 메소드, 객체를 문자열로 변환

1. 각 클래스는 toString()을 오버라이딩하여 자신만의 문자열 리턴 가능
    <1>객체를 문자열로 반환
    <2>원형

2. 컴파일러에 의한 바이트 코드
    <1>자바 소스 코드는 컴파일러에 의해 바이트 코드로 변환됩니다.
    <2>컴파일러는 toString() 메소드를 오버라이딩한 경우에도 해당 메소드를 유지하고, 오버라이딩된 내용을 반영한 바이트 코드를 생성합니다.
    <3>따라서 컴파일 이후에도 오버라이딩된 toString() 메소드가 호출되어 객체를 문자열로 변환할 수 있습니다.

객체 비교(==)와 equals()메소드
1. == 연산자
    <1>객체 레퍼런스를 비교

2. boolean.equals()
    <1>두 객체의 내용물 비교
    <2>객체의 내용물을 비교하기 위해 클래스의 맴버로 작성

wrapper 클래스
1. 자바의 기본타입을 클래스화한 8개 클래스를 통칭

2. 용도
    <1>객체만 사용할 수 있는 켈렉션 등에 기본 타입의 값을 사용하기 위해 wrapper 객체로 만들어서 사용

주요 메소드
1. 가장 많이 사용하는 integer 클래스의 주요 메소드
    <1>다른 wrapper 클래스와 메소ㅓ드는 이와 유사

2. 메소드 종류/설명
    <1>toString(): 객체를 문자열로 반환합니다.
    <2> hashCode(): 객체의 해시 코드를 반환합니다.
    <3>equals(Object obj): 다른 객체와 동등한지 여부를 판단합니다.
    <4> getClass(): 객체의 클래스 정보를 반환합니다.
    <5> notify(): 대기 중인 하나의 스레드를 깨웁니다.
    <6>notifyAll(): 대기 중인 모든 스레드를 깨웁니다.
    <7> wait(): 스레드를 일시 정지시킵니다.

박싱과 언박싱
1. 박싱
    <1>기본 타입의 값을 wrapper 객체로 변환하는 것

2. 언박싱
    <1>Wrapper 클래스의 객체에서 기본 타입의 값을 추출하는 것
    Wrapper 클래스는 기본 데이터 타입을 감싸는 클래스로, 예를 들어 Integer, Double, Boolean 등
    <2>언박싱은 Wrapper 클래스의 객체에서 intValue(), doubleValue(), booleanValue() 등의 메소드를 사용하여 해당 기본 타입의 값을 추출

String 활용
1. String의 생성:
    문자열을 생성하고 문자열 리터럴을 저장하는 객체
    쌍따옴표(" ")로 감싸진 문자열 리터럴을 사용하여 String 객체를 생성가능
    문자열 연결 (Concatenation): '+' 연산자를 사용하여 문자열을 연결가능
    문자열 리터럴과 변수, 또는 문자열 변수와 문자열 변수를 연결가능

2. 문자열의 길이 확인:
    length() 메소드를 사용하여 문자열의 길이를 확인
    공백을 포함하여 문자열의 전체 길이를 반환

3. 문자열 비교:
    equals() 메소드를 사용하여 두 문자열이 동일한지 비교
    equalsIgnoreCase() 메소드는 대소문자를 무시하고 비교

4. 부분 문자열 추출:
    substring() 메소드를 사용하여 문자열의 일부분을 추출
    시작 인덱스와 종료 인덱스를 지정하여 추출

5. 문자열 검색:
    indexOf() 메소드를 사용하여 특정 문자 또는 문자열이 처음으로 등장하는 위치확인 가능
    lastIndexOf() 메소드는 문자열의 끝에서부터 검색을 수행

6. 문자열 분할:
    split() 메소드를 사용하여 문자열을 특정 구분자를 기준으로 분할
    분할된 부분은 문자열 배열로 반환

7. 문자열 치환:
    replace() 메소드를 사용하여 문자열 내의 특정 문자 또는 문자열을 다른 문자열로 대체
    대체될 문자열이 없으면 원래 문자열을 그대로 반환

# 4월 12일 강의

Static 맴버와 non-Static 맴버 특성 정리

Static 맴버의 생성
1. static맴버는 클래스당 하나만 생성
2. 객체들에 의해 공유됨

[ 5주차 강의 내용 참고 ]-----------------------------------------
static 맴버
1. static맴버 선언
2. 객체 생성과 non-static 맴버의 생성
    --> non-static 맴버는 객체가 생설될 때, 객체마다 생긴다

static 맴버 사용
1. 클래스 이름으로 접근이 가능
2. 객체의 맴버로 접근이 가느
3. non-static 맴버는 클래스 이름으로 접근 안됨
----------------------------------------------------------------

static 메소드의 제약 조건 1
1. tatic 메소드는 오직 static 맴버만 접근 가능
    <1>객체가 생성되지 않은 상황에서도 static메소드는 실행될 수 있기 때문에 non-static 멤버 활용불가
    <2>non-static 메소드는 static맴버 사용 가능

static 메소드의 제약 조건 2
1. static 메소드는 this 사용불가
    <1>static 메소드는 객체 없이도 사용 가능하므로, this 레퍼런스 사용할 수 없음

final 클래스와 메소드
1. final 클래스 -> 더 이상 클래스 상속 불가능
2. final 메소드 -> 더 이상 오버라이딩 불가능

final 필드
1. final필드, 상수 선언
    <1>상수를 선언할 때 사용
    <2>상수 필드는 선선 시에 초기 값을 지정하여아 한다
    <3>상수 필드는 생성중에 필드 값을 초기화 할 수 없다

상속 ( Inheritance )
1. 상속의 선언
    <1>extends 키워드로 선언
        --> 부모 클래스를 물려받아 확장한다는 의미
    <2>부모 클래스 --> 슈퍼 클래스 ( super class )
    <3>자식 클래스 --> 서브 클래스 ( sub class )

서브 클래스 객체의 모양
1. 슈퍼 클래스는 객체와 서브 클래스의 객체는 별개
2. 서브 클래스 객체는 슈퍼 클래스 맴버 포함

자바 상속의 특징
1. 클래스 다중 상속 불허
    <1> c++는 다중상속 클래스가 가능

슈퍼 클래스의 맴버에 대한 서브 클래스의 접근
1. 슈퍼 클래스의 private 맴버
    <1>서브 클래스에서 접근할 수 없음

2. 슈퍼 클래스의 디폴트 맴버
    <1>서브 클래스가 동일한 패키지에 있을 때, 접근 가능

3. 슈퍼 클래스의 public맴버
    <1>서브 클래스는 항상 접근 가능

4. 슈퍼 클래스의 protected 상속

protected 맴버
1. protected 맴버에 대한 접근 
    <1>같은 패키지의 모든 클래스에 접근 허용
    <2>상속되는 서브 클래스에게 허용

서브 클래스와 슈퍼 클래스의 생성자 선택 
1. 슈퍼 클래스와 서브클래스
    <1>각각 여러개의 생성자 작성가능

서브 클래스의 객체가 생성될 때
1. 슈퍼 클래스 생성자 1개와 서브 클래스 생성자 1개가 실행

서브 클래스의 생성자와 슈퍼 클래스의 생성자가 결정된는 방식
1. 개발자의 명시적 선택

[ 업캐스팅과 다운캐스팅 설명?? ]

업캐스팅의 개념
1. 업캐스팅은 하위 클래스를 상위 클래스 타입으로 변환하는 것. 상속 계층 구조에서 자주 사용되며, 다형성을 활용하여 다양한 객체를 동일한 인터페이스로 다룰 수 있게 컴파일 타임 다형성을 제공하여 유연하고 확장 가능한 코드를 작성하는 데 도움이 됨

업캐스팅
1. 서브 클래스의 레퍼런스를 슈퍼 클래스 레퍼런스에 대입
2. 슈퍼 클래스 레퍼런스로 서브 클래스 객체를 가르키게 되는 현상

다운캐스팅의 개념
1. 다운캐스팅은 상위 클래스 타입을 하위 클래스 타입으로 변환하는 것. 업캐스팅과 반대되며 명시적인 형 변환을 필요로, 주로 상위 클래스로 선언된 객체를 실제로는 하위 클래스로 사용해야 할 때 사용되며 타입 안전성을 보장하기 위해 주의해서 사용해야 함

다운캐스팅
1. 슈퍼 클래스 레퍼런스를 서브 클래스 레퍼런스에 대입
2. 업캐스팅된 것을 다시 월래대로 되돌리는 것
3. 반드시 명시적 타입 변환 지정

[ 업캐스팅 레퍼런스로 객체 구별?? ]
업캐스팅된 레퍼런스로는 객체의 실제 타입을 구분하기 어려움
    --> 슈퍼 클래스에서 여러 서브 클래스에 상속되기 때문.


Instanceof 연산자 사용
1. 레퍼런스가 가르키는 객체의 타입 식별

instanceof 연산자의 활용예제 ( by.코드 )
[ ex.code ]-----------------------------------------------------
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        System.out.println(myDog instanceof Animal); // true
        System.out.println(myDog instanceof Dog);    // true
        System.out.println(myDog instanceof Cat);    // false

        System.out.println(myCat instanceof Animal); // true
        System.out.println(myCat instanceof Dog);    // false
        System.out.println(myCat instanceof Cat);    // true
    }
}

설명: 이 예제는 instanceof 연산자를 사용하여 각 객체가 Animal 클래스, Dog 클래스 또는 Cat 클래스의 인스턴스인지를 확인한다.
[ ex.code ]------------------------------------------------------

메소드 오버라이딩의 개념
1. 메소드 오버라이딩은 상위 클래스의 메소드를 하위 클래스에서 덮어쓰는 것, 이를 통해 하위 클래스는 상위 클래스의 동작을 재정의할 수 있다. 이 개념은 다형성을 지원하며, 런타임 시에 객체의 실제 타입에 따라 적절한 메소드가 호출이 됨

오버라이딩의 목적, 다향성 실현
1. 오버라이딩 + 다향성
    <1>하나의 인터페이스에 서로 다른 구현
    <2>슈퍼 클래스의 메소드를 서브 클래세어서 각각 목적에 맞게 다르게 구현
    <3>사례

# 4월 5일 강의

자바의 예외 클래스
1. 배열의 범위를 벗어나 원소를 접근하는 예외 처리
<1> 자바에서 배열의 범위를 벗어나 원소를 접근하려고 할 때 발생하는 예외는 ArrayIndexOutOfBoundsException이고, 이 예외는 배열의 유효하지 않은 인덱스에 접근하려고 할 때 발생 배열의 유효한 인덱스 범위는 0부터 배열의 길이 - 1까지이며, 이 범위를 벗어나는 인덱스에 접근하면 이 예외가 발생

[ 세상 모든 것이 객체다..? ]
자바의 객체지향 특성 : 캡슐화
1. 캡슐화 : 객체를 캡술로 써서 내부를 볼 수 없게 하는것
    <1> 객체의 가장 본직적인 특징
    <2> 외부의 접근으로부터 객체 보호

자바의 캡슐화
1. 클래스 : 객체 모양을 선헌한 툴( 캡슐화 하는 틀 )
    --> 클래스 내에 메소드와 필드를 구현함

자바의 객체 지향 특성 : 상속
    <1>상위 개체의 속성이 하위 개체에 물려짐
    <2>하위 개체가 상위 개체의 속성을 모두 가지는 관계

자바_상속
1. 상위 클래스의 맴버를 하위 클래스가 물려받음
    <1>상위 클래스 : 수피 클래스
    <2>하위 클래스 : 서브 클래스, 수피 클래스 코드의 재사용, 새로운 특성 추가 기능

자바의 객체 지향 특성 : 다향성
1. 같은 이름의 메소드가 클래스 혹은 객체에 따라 다르게 구현되는 것
2. 다향성 사례
    <1>메소드 오버로딩 : 한 클래스 내에서 같은 이름이지만 다르게 작동하는 여러 메소드
    <2>메소드 오버라이딩 : 슈퍼 클래스의 메소드를 동일한 이름으로 서브 클래스마다 다르게 구현

객체 지향 언어의 목적
1. 소프트웨어의 생산성 향상
    <1>소프트웨어를 빠른 속도로 생산할 필요성 중대
2. 객체 지향 언어
    <1>상속, 다향성, 객체, 캡슐화 등 소프트웨어 재사용시간을 위한 장치 내장
    <2>소프트웨어 재사용과 부분 수정 빠름
    <3>소프트웨어를 다시 만드는 부담 대폭 줄임
    <4>소프트웨어 생산성 향상

3. 초기 프로그래밍
    <1>수학 계산/통계 처리를 하는 등 처리 과정, 계산 절차 중요
4. 현대 프로그래밍
    <1>컴퓨터가 산업 절반에 활용
    <2>실세계에서 발생하는 일을 프로그래밍

5. 객체지향 언어에 대한 총괄 설명 ( from_chatGPT )
클래스(Class): 클래스는 객체를 생성하기 위한 템플릿이며, 객체의 설계도라고 할 수 있습니다. 클래스는 객체의 속성을 정의하는 데이터 필드와 해당 데이터를 다루는 메서드를 포함합니다.

객체(Object): 클래스의 인스턴스로, 메모리에 할당된 실제 데이터입니다. 객체는 데이터와 해당 데이터를 처리하기 위한 메서드를 가지고 있습니다.

상속(Inheritance): 상속은 기존 클래스의 속성과 메서드를 다른 클래스가 재사용할 수 있도록 하는 개념입니다. 부모 클래스(슈퍼 클래스)의 특성을 자식 클래스(서브 클래스)가 상속받아 확장하거나 수정할 수 있습니다.

다형성(Polymorphism): 다형성은 같은 이름의 메서드가 서로 다른 기능을 할 수 있도록 하는 개념입니다. 이는 메서드 오버로딩(Overloading)과 메서드 오버라이딩(Overriding)을 통해 구현될 수 있습니다.

캡슐화(Encapsulation): 캡슐화는 데이터와 해당 데이터를 처리하는 메서드를 하나의 단위로 묶는 개념입니다. 이를 통해 데이터를 보호하고 외부에서 직접 접근하는 것을 제어할 수 있습니다.

객체지향언어를 사용하면 코드를 모듈화하고 재사용성을 높일 수 있으며, 코드의 가독성과 유지보수성을 향상시킬 수 있습니다. 또한 실제 세계의 개념을 쉽게 모델링할 수 있어서 문제 해결에 있어서 직관적이고 유연한 설계를 할 수 있습니다. Java, C++, Python, C#, 등 많은 프로그래밍 언어가 객체지향언어의 개념을 지원하고 있습니다.

절차 지향 프로그래밍과 객체 지향 프로그래밍
1. 절차 지향 프로그래밍
    <1>작업 순서를 표현하는 컴퓨터 명령 집합
    <2>함수들의 집합으로 프로그램 작성

2. 객체 지향 프로그래밍
    <1>객체 지향 프로그래밍은 현실 세계의 객체들을 모델링하여 소프트웨어를 개발하는 방법으로, 클래스와 객체를 중심으로 데이터와 기능을 조직화하여 코드를 구성, 이를 통해 코드의 재사용성을 높이고 유지보수를 용이하게 하며, 복잡한 문제를 더 직관적이고 구조화된 방식으로 해결할 수 있다.

클래스와 객체
1. 클래스
    <1>객체의 속성과 행위 선언
    <2>객체의 설계도 혹은 틀

2. 객체
    <1>클래스의 틀로 찍어낸 실체
        -->프로그램 실행 중에 생성되는 실체
        -->메모리 공간을 갖는 구체적인 실체 
        -->인스턴트 라고도 부름
3. 사례 ( EX )
    <1>클래스 : 소나타자동차
    <2>클래스 : 벽시계
    <2>클래스 : 책상

클래스와 객체와의 관계/차이 ( 추가설명 )
1. 클래스는 객체를 생성하기 위한 템플릿이며, 객체의 설계도 역할을 수행, 객체는 클래스의        인스턴스로, 메모리에 할당된 실제 데이터이고, 클래스는 객체를 생성하기 위한 구조를 정의하고, 객체는 클래스를 기반으로 생성되어 실제 작업을 수행

자바 클래스 구성
1. 클래스 이름: 클래스의 이름은 대소문자를 구분하는 식별자로, 클래스를 식별하는 데 사용

2. 속성(멤버 변수): 클래스 내부에는 속성이 포함될 수 있습니다. 이러한 속성들은 클래스의 상태를 나타내며, 멤버 변수로 선언

3. 메서드(멤버 함수): 클래스에는 데이터를 처리하거나 특정 동작을 수행하는 메서드가 포함됩니다. 이러한 메서드들은 클래스의 동작을 정의하고 구현

4. 생성자(Constructor): 생성자는 객체가 생성될 때 호출되는 특수한 종류의 메서드로, 객체의 초기화를 담당

5. 접근 제어 지시자(Access Modifiers): private, protected, public 등의 접근 제어 지시자를 사용하여 클래스의 멤버 변수와 메서드에 대한 접근 권한을 제어할 수 있음

6. 내부 클래스(Inner Class): 클래스 내부에 다른 클래스를 선언하는 것이 가능합니다. 이러한 내부 클래스는 외부 클래스의 멤버에 쉽게 접근할 수 있음

생성자의 특징
1. 생성자 이름은 클래스 이름과 동일
2. 생성자는 여러 개 작성 가능 ( 생성자 중복)
3. 생성자는 객체 생성 시 한 번만 호출
4. 생상자의 목적은 객체 생성 시 초기화
5. 생성자는 리턴 타입을 지정할 수 없음

기본 생성자가 자동 생성되지 않는 경우
1. 클래스에 생성자가 선언되어 있는 경우
    <1>컴파일러는 기본 생성자를 자동 생성해 주지 않는다

this 래퍼런스
1. this
    <1>객체 자신에 대한 레퍼런스
        --> 컴파일러에 의해 자동 관리, 개발자는 사용하기만 하면 됨
        --> this 맴버 형태로 맴버를 접근할 때 사용
2. this()로 다른 생성자 호출
    <1>this()
        --> 현재 클래스의 다른 생성자 호출: this()는 같은 클래스 내의 다른 생성자를 호출
        --> 생성자 중복 최소화: 생성자들 간의 중복된 초기화를 피하기 위해 사용됩니다.
        --> 초기화 과정 단순화: 공통된 초기화 코드를 간결하게 작성할 수 있습니다.

객체 배열
1. 자바의 객체 배열
    <1>객체에 대한 레퍼런스 배열함
2. 자바의 객체 배열 만들기 3단계
    <1> 여러 객체 저장: 객체 배열은 여러 개의 객체를 저장하는 자료 구조
    <2> 인덱스로 접근: 각 객체는 배열의 인덱스를 통해 접근
    <3> 객체 생성과 관리: 동일한 클래스의 객체를 생성하고 관리하는데 사용

메소드
1. 메소드 설명
    <1>메소드는 c/c++의 함수와 동일
    <2>자바의 모든 메소드는 반드시 클래스 안에 있어야 함 ( 캡슐화 원칙 )

2. 메소드 설정
    <1> 메소드 정의: 클래스 내부에 특정 기능을 수행하는 코드 블록을 포함
    <2> 메소드 호출: 메소드를 실행하기 위해 해당 메소드의 이름과 필요한 매개변수를 전달, 실행
    <3> 메소드 설정: 메소드의 속성을 결정하는 과정으로, 접근 제어자, 반환 유형, 예외 처리 등을 설정, 메소드의 동작을 조정

객체 소멸
1. 객체 소멸 설명
    <1>new에 의해 할당 받은 객체와 배열 메모리를 자바 가상 기계로 되돌려 주는 행위
    <2>소멸된 객체 공간으 ㄴ가용 메모리에 포함
2.자바에서 사용자의 임의로 객체 소멸안됨
    <1>자바는 객체 소멸 연산자 없음
        -->객체 생성 연산자 : new
    <2>객체 소멸은 자바 가상 기계의 고유한 역활
    <3>자바 개발자에게는 매우 다행스러운 기능
        -->c/c++에서 할당 받은 객체를 개발자가 프로그램 내에서 삭제해야함
        -->c/c++의 프로그램 작성을 어렵게 만드는 요인
        -->c/c++에서는 사용하지 않는 객체나 배열을 돌려주는 코딩의 책임을 사용자에게서 하지않음

가비지
1. 가비지 설명
    <1>가리키는 레퍼런스가 하나도 없는 객체
        -->더 이상 접근할 수 없어 사용할 수 없게 된 메모리

2. 가비지 컬랙션
    <1>자바 가상 기계의 컬랙터가 자동으로 가바지 수집, 반환

3. 강제 가비지 컬랙션
1. System 또는 Runtime 객체의 gc() 메소드를 호출
    --> 이 코드는 자바 가상 기계에 강력한 가비지 컬렉션 요청
    --> 그러나 자바 가상 시계가 가비지 컬렉션 시점을 전적으로 판단

자바의 패키지 개념
1. 패키지 설명
    <1>상호 관련 있는 클래스 파일 ( 컴파일된, .class) 을 저장하여 관리는 디렉터리
    <2>자바 응용프로그램은 하나 이상의 패키지로 구성

접근 지정자
1. 자바의 접근 지정자 설명
    <1> 4가지 ( public, protective, defalut, private )
2. 접근 지정자의 목적
    <1>클래스나 일부 맴버를 공개하여, 다른 클래스에서 접근하도록 허용
    <2>객체 지향 언어의 캡술화 정책은 맴버를 보호하는 것
3. 접 근 지정자에 따른 클래스나 맴버의 공개 범위

클래스의 접근 지정
1. 클래스 접근지정 설명
    <1>다른 클래에서 사용하도록 허용할지 지정
    <2>public 클래스
        --> 다른 모든 클래스에게 접근 허용
    <3>디폴트 클래스 ( 접근지정자 생략 )
        -->package ----- private라고 함

맴버 접근 지정
1. public
    --> 어떤 클래스의 인스턴스든 해당 멤버에 접근할 수 있도록 함. 다른 패키지에서도 접근이 가능
2. protective
    --> 동일한 패키지에 있는 클래스와 해당 클래스를 상속받은 클래스에서만 해당 멤버에 접근
3. default
    --> 동일한 패키지 내의 클래스에서만 해당 멤버에 접근
4. private
    --> 해당 멤버에 접근할 수 있는 범위를 해당 클래스 내부로 제한, 다른 클래스에서는 해당 멤버에 직접 접근이 불가

static 맴버
1. static맴버 선언
2. 객체 생성과 non-static 맴버의 생성
    --> non-static 맴버는 객체가 생설될 때, 객체마다 생긴다

static 맴버 사용
1. 클래스 이름으로 접근이 가능
2. 객체의 맴버로 접근이 가느
3. non-static 맴버는 클래스 이름으로 접근 안됨

# 3월 29일 강의

Scanner 클래스
읽은 바이트를 문자,정수,실수,불린,문자열 등으로 변환하여 리턴
java.util.scanner

scanner는 입력되는 키 값을 공백으로 구분되는 토큰단위로 읽음
--> 개발자가 읽기 쉬운 타입 값으로 읽을 수 있음

식과 연산자
연산

산술 연산자  < + , -, *, /, % >
더하기, 빼기, 곱하기, 나누기, 나눈 값 의 나머지<1>주어진 식을 계산하여 결과를 얻어내는 과정

조건 연산자
3개의 피연산자로 구성된 삼항 연산자
if-else을 조건연산자로 간결하게 표현 가능

비트 연산
비트의 개념<1>비트끼리 and, or, xor, not연산

비트 논리 연산<1> 피연산자들의 각 비트들을 대상으로하는 연산

조건무 단순 if문, if-else문

switch 문
스위치문은 식과 케이스 문의 값과 비교
    <1>case의 비교 값과 일치하면 해당 케이스의 실행문장 수행
    --> breack를 만나면 빠져나감

반복문
자바의 반복문 for , while, do-while 문<1> for문은 가장 많이 사용하는 문<2> while문은 조건식이 "참"인 경우 반복 실행

    <3> do-while문은 일정한 조건이 참일 때까지 특정한 작업을 반복 실행

자바 배열
1. 배열(array)
    <1>인덱스와 인덱스에 대응하는 데이터들로 이루어진 자료구조
        -->배열을 이용하면 한 번에 많은 메모리 공긴 선언 가능
    <2>배열은 같은 타입의 데이터들이 순차적으로 저장되는 공간
        -->원소 데이터들이 순차적으로 저장
        -->인덱스를 이용하여 원소 데이터 접근
        -->반복문을 이용하여 처리하기에 적합한 자료구조
2. 배열 인덱스
    <1>0 부터 시작
    <2>인덱스는 배열의 시작 위치에서부터 데이터가 있는 상대 위치까지

배열 선언 및 생성 디테일
1. 배열 선언과 배열 생성의 두 단계 필요
    <1>배열 선언
        -->배열의 이름 선언
        -->배열 생성
        -->배열 초기화

배열 인덱스와 배열 원소 접근
1. 배열 인덱스
    <1>배열 인덱스는 0 ~ ( 배열의 크기 - 1 )
[ ex: code ]
--> 배열 선언
my_array = [10, 20, 30, 40, 50]

--> 배열의 크기
array_size = len(my_array)

--> 배열의 인덱스와 원소 접근
for i in range(array_size):
    print(f"인덱스 {i}: {my_array[i]}")

--> 마지막 요소에 접근
last_index = array_size - 1
last_element = my_array[last_index]
print(f"마지막 요소의 인덱스 {last_index}: {last_element}")

레퍼런스 치환과 배열 공유
1. 레퍼런스 치환으로 두 레퍼런스가 하나의 배열 공유
[ ex: code ]
let array1 = [1, 2, 3, 4];
let array2 = array1; // 레퍼런스 치환

console.log(array1); // [1, 2, 3, 4]
console.log(array2); // [1, 2, 3, 4]

array1[0] = 5;

console.log(array1); // [5, 2, 3, 4]
console.log(array2); // [5, 2, 3, 4]

배열의 크기, length필드
1. 자바의 배열은 객체로 처리
    <1>배열 객체의 length필드
        -->배열의 크기는 배열 객체의 length 필드에 저장
    <2>length필드를 이용하여 배열의 모든 값을 출력하는 사례
    [ ex : code ]
    public class ArrayExample {
    public static void main(String[] args) {
        // 배열 선언, 생성, 초기화
        int[] numbers = {10, 20, 30, 40, 50};
        
        // 배열의 길이를 이용하여 모든 요소 출력
        System.out.println("배열의 요소:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("numbers[" + i + "] = " + numbers[i]);
        }
    }
}

배열과 for-each문
1. for-each문
    <1>주어진 배열 또는 컬렉션의 크기만큼 반복하면서 각 요소에 접근할 수 있습니다.

2차원 배열
1. 2차원 배열은 행과 열로 구성된 배열이며, 각 요소에는 두 개의 인덱스를 사용하여 접근
2. 2차원 배열을 만들 때는 배열 안에 배열을 넣는 방식으로 표현하고, 이를 통해 행과 열의 관계를 표현
3. 2차원 배열을 이용할 때는 이중 반복문을 통해 각 요소에 접근하고 처리

# 3월 22일 강의
내용정리

명령어 : 컨트롤 + 쉬프트 + p
--> java pro까지만 검색 
--> java create java project 선택 
--> 파일지정

자바 --> .java파일은 버전이 모두 다르고 사용환경 (ex: 윈도우, 맥, 리눅스 등)
모두 다르기 때문에 윈도우에서 작업한 파일은 맥에서 작동하지 않는다.
따라서 .java파일을 .class 파일로 변환하여 저장하면 모든 플랫폼에서 작업이 가능하다.

이 과정에 필요한 것은 "자바 버추얼 머신( 통칭: JVM )" 이다

개발하는 과정에서 필요한 것은 ( JDK ) 이다
사용하는 입장에서 필요한 것은 ( JRE ) 이다

서블릿은 웹브라우저에서 실행되는 자바스킄립트 코드와 통신

*자바 API
JDK에 포함된 클래스 라이브러리 
주요한 기능들을 미리 구현한 클래스 라이브러리의 집합
API에서 정의한 규격에 따라 글래스 사용
자바 패키지 
서로 관련된 클래스들을 분류하여 묶어 놓은 것
   클래스의 이름에 패키지 이름도 포함
   다른 패키지의 동일한 이름의 클래스 존재 가능
*자바 통합 개발 환경-이클립스

*IDE
 -통합 개발 환경 
 -편집,컴파일,디버깅을 한번에 할 수 있는 통합된 개발 환경

*이클립스
 -자바 응용 프로그램 개발을 위한 통합 개발 환경
 -IBM에 의해 개발된 오픈 소스 프로젝트

*자바 모바일 응용 : 안드로이드 앱


※자바의 특징
 -플랫폼 독립성
 -객체지향-캡슐화,상속,다형성 지원
 -클래스의 캡슐화
 -자바의 모슨 변수나 함수는 클래스 내에 선언
 -클래스 안에서 클래스(내부 클래스) 작성 가능
 -소스(.java) 와 클래스 (.class) 파일
 -하나의 소스 파일에 여러 클래스를 작성 가능 - public 클래스는 하나만 가능
 -소스 파일의 이름과 public으로 선언된 클래스 이름은 같아야 함
 -클래스 파일에는 하나의 클래스만 존재- 다수의 클래스를 가진 자바 소스를 컴파일하면 클래스마다 별도 클래스 파일 생성

 *실행코드 배포
  -한개의 class파일 또는 다수의 class파일로 구성
  -여러 폴더에 걸쳐 다수의 클래스 팡일로 구성된 경우 : jar 압축 파일로 배포
  -자바 응용프로그램의 실행은 main() 메소드에서 시작
  -하나의 클레스 파일에 두개 이상의 main() 메소가 있을수 없음.

 *패키지
  -서로 관련 있는 여러 클래스를 패키지로 묶어 관리
  -패키지는 폴더 개념

 *멀티스레드 
  -여러 스레드의 동시 수행 환경 지원
  -자바는 운영체계의 도움 없이 자체적으로 멀티 스레드 지원
  -C/C++프로그램은 멀티스레드를 위해 운영체게 API를 호출

 *가비지 컬랙션
  -자바 언어는 메모리 할당 기능은 있어도 메모리 변환 기능은 없음

 *실시간 응용프로그램에 부적합
  -실행 도중 예측할 수 없는 시점에 가비지 컬랙션 실행 때문
  -응용프로그램의 일시적 중단 발생

 *자바 프로그램은 안전
  -타입 체크 엄격
  -물리적 주소를 사용하는 포인터 개념 없음

 *프로그램 작성 쉬움
  -포인터 개념 없음
  -동적 메모리 반환 하지 않음
  -다양한 라이브러리 지원

 *실행 속도 개선을 위한 JTI 컴파일러 사용
  -자바는 바이트 코드를 인터프라터 방식으로 실행
   -기계어가 실행되는 것보다 느림
  -JIT 컴파일 기법으로 실행 속도 개선
   -JTI 컴파일 - 실행 중에 바이트 코드를 기계어 코드로 컴파일하여 기계어를 실행하는 기법

----------------------------------------------------------------------------------------


[ 식별자 ]
1. 클래스, 변수, 상수, 메소드 등에 붙이는 이름
2. 식별자의 원칙
 <1>특수문자와 공백 또는 탭은 식별자로 사용이 불가능하다
 <2>유니코드 문자 사용가능, 한글도 사용가능
 <3>자바언어의 키워드는 식별자로 사용불가
 <4>식별자의 첫 번째 문자는 숫자로 불가능

[ 자바 데이터 타입 종류 ]
boolean
char
byte
short
int
long
float
double

[ 리터럴과 정수 리터럴 ]
1. 리터럴
    <1>프로그램에서 직접 표현한 값
    <2>정수, 실수, 문자, 논리, 문자열 리터럴이 있음
2. 정수 리터럴
    <1>10진수, 16진수, 8진수 등..

[ 실수 리터럴 ]
1. 소수점 형태나 지수 형태로 표현한 실수
    <1>12, 12.0, 12.00, 12.000
2. 실수 타입 리터럴은 double로 표현

[ 문자 리터럴 ]
1. 단일 인용부호 (**)로 문자 표현
2. 특수문자 리터럴은 백슬래시| ( \ ) 로 표현 함

[ 상수 ]
1. 상수 선언
    <1>final 키워드 사용
    <2>선언 시 초기값 지정
    <3>실행 중 값 변경 불가

[ var 키워드 ]
* JAVA 10 부터 도입됨 *
1. 기존 변수선언 방식 : 변수의 타입 반드시 지정
2. var키워드
    <1>타입을 생략하고 변수 선언 가능
    <2>컴파일러가 추론하여 변수 타입 결정
    <3>변수 선언 시 초기값이 주어지지 않으면 컴파일 오류

[ 타입 변환 ]
1. 한 타입의 값을 다른 타입의 값으로 변환

[ 자동 타입 변환 ]
1. 컴파일러에 의한 월래의 타입보다 큰 타입으로 자동 변환









# 3월 15일 강의
내용정리
