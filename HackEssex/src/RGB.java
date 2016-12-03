public class RGB
{
    private int R;
    private int G;
    private int B;

    public RGB(int R, int G, int B)
    {
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public int getR()
    {
        return this.R;
    }

    public void setR(int r)
    {
        this.R = r;
    }

    public int getG()
    {
        return this.G;
    }

    public void setG(int g)
    {
        this.G = g;
    }

    public int getB()
    {
        return this.B;
    }

    public void setB(int b)
    {
        this.B = b;
    }

    public String toString()
    {
        return "(" + getR() + "," + getG() + "," + getB() + ")";
    }
}
