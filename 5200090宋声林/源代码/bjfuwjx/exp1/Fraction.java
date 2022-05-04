import java.math.BigInteger;
import java.util.Objects;

/**
 * @author SongShengLin
 * @date 2022/5/3 20:49
 * @description 实验1：分数类
 */
public class Fraction implements Comparable<Fraction> {

    /**
     * 分数的正负号，正数1，负数-1，零0
     */
    private int sign = 0;

    /**
     * 分数的分子
     */
    private BigInteger numerator = BigInteger.ZERO;

    /**
     * 分数的分母
     */
    private BigInteger denominator = BigInteger.ZERO;

    /**
     * 分数常量 0/1
     */
    public static final Fraction ZERO = new Fraction(0, 1);

    /**
     * 分数常量 +1/1
     */
    public static final Fraction ONE = new Fraction(1, 1);

    /**
     * 分数常量 -1/0，即负无穷
     */
    public static final Fraction NEGATIVE_INFINITY = new Fraction(-1, 0);

    /**
     * 分数常量 +1/0，即正无穷
     */
    public static final Fraction POSITIVE_INFINITY = new Fraction(1, 0);

    /**
     * 分数常量，0/0，即：不是一个分数
     */
    public static final Fraction NAN = new Fraction(0, 0);

    /**
     * 测试方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        testSimple();
//        testComplex();

    }


    /**
     * 构造方法：由两个大整数构造一个分数，大整数带正负号，最终的分数的正负号通过计算获得。
     * 需要进行处理，包括符号处理，也包括约分处理。
     *
     * @param primNumerator   输入的分子，最终的构造的分数的分子很可能不是这个输入。
     * @param primDenominator 输入的分母，最终的构造的分数的分母很有可能不是这个输入。
     */
    public Fraction(BigInteger primNumerator, BigInteger primDenominator) {
        if (primNumerator.compareTo(BigInteger.ZERO) > 0 && primDenominator.compareTo(BigInteger.ZERO) > 0) {
            this.sign = 1;
        } else if (primNumerator.compareTo(BigInteger.ZERO) < 0 && primDenominator.compareTo(BigInteger.ZERO) < 0) {
            this.sign = 1;
        } else if (primNumerator.compareTo(BigInteger.ZERO) > 0 && primDenominator.compareTo(BigInteger.ZERO) < 0) {
            this.sign = -1;
        } else if (primNumerator.compareTo(BigInteger.ZERO) < 0 && primDenominator.compareTo(BigInteger.ZERO) > 0) {
            this.sign = -1;
        } else if (primNumerator.compareTo(BigInteger.ZERO) == 0 && primDenominator.compareTo(BigInteger.ZERO) != 0) {
            this.sign = 0;
        } else if (primNumerator.compareTo(BigInteger.ZERO) != 0) {
            this.sign = primNumerator.compareTo(BigInteger.ZERO) > 0 ? 1 : -1;
        }
        this.numerator = primNumerator.abs();
        this.denominator = primDenominator.abs();
    }


    /**
     * 构造方法：指定正负号
     *
     * @param sign        正负号
     * @param numerator   分子
     * @param denominator 分母
     */
    public Fraction(int sign, BigInteger numerator, BigInteger denominator) {
        this.sign = Integer.compare(sign, 0);
        this.numerator = numerator.abs();
        this.denominator = denominator.abs();
    }

    /**
     * 用两个长整型构造一个分数。
     *
     * @param lNumerator   输入的长整数分子。
     * @param lDenominator 输入的长整数分母。
     */
    public Fraction(long lNumerator, long lDenominator) {
        if (lNumerator > 0 && lDenominator > 0) {
            this.sign = 1;
        } else if (lNumerator < 0 && lDenominator < 0) {
            this.sign = 1;
        } else if (lNumerator > 0 && lDenominator < 0) {
            this.sign = -1;
        } else if (lNumerator < 0 && lDenominator > 0) {
            this.sign = -1;
        } else if (lNumerator == 0 && lDenominator != 0) {
            this.sign = 0;
        } else if (lNumerator != 0) {
            this.sign = Long.compare(lNumerator, 0);
        }
        this.numerator = new BigInteger(String.valueOf(lNumerator)).abs();
        this.denominator = new BigInteger(String.valueOf(lDenominator)).abs();
    }


    /**
     * 获取本分数的正负号，用-1、0、1表示。
     *
     * @return 本分数的正负号。
     */
    public int getSign() {
        return sign;
    }

    /**
     * 获取本分数的分子，注意分子恒为非负。
     *
     * @return 本分数的分子。
     */
    public BigInteger getNumerator() {
        if (Objects.isNull(numerator)) {
            throw new RuntimeException("分子不存在，请调用构造器生产Fraction");
        }
        return numerator;
    }

    /**
     * 获取本分数的分母。注意分母恒为非负。
     *
     * @return 本分数的分母。
     */
    public BigInteger getDenominator() {
        if (Objects.isNull(denominator)) {
            throw new RuntimeException("分母不存在，请调用构造器生产Fraction");
        }
        return denominator;
    }

    /**
     * 用一个长整数构造一个分数，默认分数的分母是1。
     *
     * @param integerValue 输入的长整数，其绝对值与构造生成的分数的分子相同。
     */
    public Fraction(long integerValue) {
        new Fraction(new BigInteger(String.valueOf(integerValue)), BigInteger.ONE);
    }


    /**
     * 判断此分数是否为“非数”，也就是是否是“0/0”类型。
     *
     * @return 如果是一个非数，返回true，否则返回false
     */
    public boolean isNaN() {
        if (numerator.compareTo(BigInteger.ZERO) == 0 && denominator.compareTo(BigInteger.ZERO) == 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断此分数是否为0，也就是是否为“0/1”这各类型的分数。
     *
     * @return 是的话返回true；否则返回false
     */
    public boolean isZero() {
        if (numerator.compareTo(BigInteger.ZERO) == 0 && denominator.compareTo(BigInteger.ZERO) != 0) {
            return Boolean.TRUE;
        } else if (numerator.compareTo(BigInteger.ZERO) != 0 && denominator.compareTo(BigInteger.ZERO) == 0) {
            throw new RuntimeException("分母不能为null或者0");
        }
        return Boolean.FALSE;
    }

    /**
     * 判断此分数是否是正的分数；当然，正无穷也是正的分数。
     *
     * @return 是的话返回true，否则返回false。
     */
    public boolean isPositive() {
        if (Objects.equals(sign, 1)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 判断此分数是否为负的，当然负无穷也是负的。
     *
     * @return 如果是负分数，则返回true，否则返回false。
     */
    public boolean isNegative() {
        return !isPositive();
    }

    /**
     * 判断此分数是否为无穷大，包括正无穷和负无穷。
     *
     * @return 如果是无穷大，则返回true，否则返回false。
     */
    public boolean isInfinite() {
        return denominator.compareTo(BigInteger.ZERO) == 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 把本分数的内容全部拷贝，生成一个新的分数返回。
     *
     * @return 本分数的一个复制版本。
     */
    @Override
    public Fraction clone() {
        try {
            return (Fraction) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 求本分数的绝对值并返回。
     *
     * @return 本分数的绝对值，也是一个分数。
     */
    public Fraction abs() {
        return new Fraction(1, numerator, denominator);
    }

    /**
     * 求本分数的相反数，也是一个分数。
     *
     * @return 本分数的相反数。
     */
    public Fraction opposite() {
        return new Fraction(-sign, numerator, denominator);
    }

    /**
     * 求本分数的倒数，也是一个分数。正负无穷的倒数都是0，但0的倒数规定为正无穷而不是负无穷。
     *
     * @return 本分数的倒数。
     */
    public Fraction reciprocal() {
        if (denominator.compareTo(BigInteger.ZERO) == 0) {
            return ZERO;
        } else if (numerator.compareTo(BigInteger.ZERO) == 0) {
            return POSITIVE_INFINITY;
        }
        return new Fraction(sign, denominator, numerator);
    }

    /**
     * 绝对值比较大小。本分数的绝对值和that的绝对值比较大小。
     * 把NaN当作0处理；正无穷和正无穷比较为相等。
     *
     * @param that 与本分数待比较的分数。
     * @return 当本分数的绝对值和that的绝对值相等时返回0；本分数绝对值小的时候返回-1，绝对值大的时候返回1。
     */
    private int absCompareTo(Fraction that) {
        Fraction thisAbs = new Fraction(numerator, denominator);
        Fraction thatAbs = that.abs();
        return thisAbs.compareTo(thatAbs);
    }

    /**
     * 两个分数比较大小。
     *
     * @param that 与本分数待比较的分数。
     * @return 如果两个分数值相等，返回0；如果本分数小，返回-1；否则返回1。
     */
    @Override
    public int compareTo(Fraction that) {
        int thatSign = that.getSign();
        BigInteger thatNumerator = that.getNumerator();
        BigInteger thatDenominator = that.getDenominator();
        if (Objects.equals(sign, thatSign) && Objects.equals(numerator, thatNumerator) && Objects.equals(denominator, thatDenominator)) {
            return 0;
        } else if (sign > thatSign) {
            return 1;
        } else if (sign < thatSign) {
            return -1;
        }
        BigInteger thisDecimal = numerator.divide(denominator).multiply(new BigInteger(String.valueOf(sign)));
        BigInteger thatDecimal = thatNumerator.divide(thatDenominator).multiply(new BigInteger(String.valueOf(thatSign)));
        return thisDecimal.compareTo(thatDecimal);
    }

    /**
     * 两个非负分数相加。
     *
     * @param that 与本分数待相加的分数。
     * @return 两个分数相加的和，仍然时一个分数。
     */
    private Fraction positiveAdd(Fraction that) {
        return new Fraction(1, numerator.add(that.getNumerator()), denominator.add(that.getDenominator()));
    }

    /**
     * 两个非负的分数相减。
     *
     * @param smaller 这是减数，本分数时被减数。
     * @return 返回两个分数的差。
     */
    private Fraction positiveSubtract(Fraction smaller) {
        return new Fraction(1, numerator.subtract(smaller.getNumerator()), denominator.subtract(smaller.getDenominator()));
    }

    /**
     * 两个分数相加。
     *
     * @param that 加数。本分数是被加数。
     * @return 两个分数的加和。
     */
    public Fraction add(Fraction that) {
        int thatSign = that.getSign();
        BigInteger thatNum = that.getNumerator();
        BigInteger thatDen = that.getDenominator();

        int newSign = 1;
        BigInteger newNum = null;
        BigInteger newDen = denominator.multiply(thatDen);
        // ++ -- 00(++)
        if (Objects.equals(sign, thatSign)) {
            newSign = Integer.compare(sign, 0);
            newNum = numerator.multiply(thatDen).add(denominator.multiply(thatNum));
        } else {
            // +- -+ 有一个为0
            // this符号为0
            if (Objects.equals(sign, 0)) {
                if (thatSign > 0) {// 0 +
                    newNum = numerator.multiply(thatDen).add(denominator.multiply(thatNum));
                } else {// 0 -
                    newNum = numerator.multiply(thatDen).subtract(denominator.multiply(thatNum));
                    if (newNum.compareTo(BigInteger.ZERO) < 0) {
                        newSign = -1;
                    }
                }
            } else if (Objects.equals(thatSign, 0)) {
                if (sign > 0) {// + 0
                    newNum = numerator.multiply(thatDen).add(denominator.multiply(thatNum));
                } else {// - 0
                    newNum = thatNum.multiply(denominator).subtract(thatDen.multiply(numerator));
                    if (newNum.compareTo(BigInteger.ZERO) < 0) {
                        newSign = -1;
                    }
                }
            } else if (sign > 0 && thatSign < 0) {// +-
                newNum = numerator.multiply(thatDen).subtract(denominator.multiply(thatNum));
                if (newNum.compareTo(BigInteger.ZERO) < 0) {
                    newSign = -1;
                }
            } else if (sign < 0 && thatSign > 0) {// -+
                newNum = thatNum.multiply(denominator).subtract(thatDen.multiply(numerator));
                if (newNum.compareTo(BigInteger.ZERO) < 0) {
                    newSign = -1;
                }
            }
        }


        // 约分
        BigInteger gcd = newNum.gcd(newDen);
        newNum = newNum.divide(gcd);
        newDen = newDen.divide(gcd);

        return new Fraction(newSign, newNum, newDen);
    }


    /**
     * 两个分数相减。
     *
     * @param that 减数。
     * @return 本分数减去that的差。
     */
    public Fraction subtract(Fraction that) {
        return add(new Fraction(that.getSign() * (-1), that.getNumerator(), that.getDenominator()));
    }

    /**
     * 一个分数和另一个分数相乘。
     *
     * @param factor 乘数。本分数是被乘数。
     * @return 两个分数的积，仍然是一个分数。
     */
    public Fraction multiply(Fraction factor) {
        BigInteger thatNum = factor.getNumerator();
        BigInteger thatDen = factor.getDenominator();

        BigInteger newNum = numerator.multiply(thatNum);
        BigInteger newDen = denominator.multiply(thatDen);

        BigInteger gcd = newNum.gcd(newDen);

        if (Objects.equals(sign, 0)) {
            return new Fraction(Integer.compare(factor.getSign(), 0), newNum.divide(gcd), newDen.divide(gcd));
        }
        return new Fraction(Integer.compare(sign * factor.getSign(), 0), newNum.divide(gcd), newDen.divide(gcd));
    }

    /**
     * 本分数乘以一个长整数因子，获取一个新的分数。
     *
     * @param ratio 待乘的因子。
     * @return 放大后新的分数。
     */
    public Fraction multiply(long ratio) {
        return new Fraction(sign, numerator.multiply(new BigInteger(String.valueOf(ratio))), denominator);
    }

    /**
     * 获取本分数除以另外一个分数的商。
     *
     * @param divisor 除数。
     * @return 商。
     */
    public Fraction divide(Fraction divisor) {
        return multiply(new Fraction(divisor.getSign(), divisor.getDenominator(), divisor.getNumerator()));
    }

    /**
     * 获取本分数除以一个长整数因子后的商。
     *
     * @param ratio 除数，一个长整数因子。
     * @return 商。
     */
    public Fraction divide(long ratio) {
        return multiply(new Fraction(1 / ratio));
    }


    /**
     * 重写toString
     *
     * @return 例如+100/101
     */
    @Override
    public String toString() {
        return (sign >= 0 ? "+" : "-") + numerator.toString() + "/" + denominator.toString();
    }

    /**
     * 一个简单的测试。计算 1/(1*2) + 1/(2*3) + ... + 1/(100*101)
     * 事实上，上式等于 (1/1 - 1/2) + (1/2 - 1/3) + ... + (1/100 - 1/101)
     * 最后的结果应该是 "+100/101"。
     */
    public static void testSimple() {
        Fraction sum = ZERO;
        for (int i = 1; i <= 100; i++) {
            int denominator = i * (i + 1);
            Fraction addend = new Fraction(1, denominator);
            sum = sum.add(addend);
        }
        System.out.println(sum);
    }

    /**
     * 一个复杂的测试：计算 [ 1/(1*2) + 1/(2*3) + ... + 1/(2022*2023) ] - [ 1/(1*3) + 1/(3*5) + ... + 1/(2021*2023) ]
     * 被减数最后的结果是 +2022/2023，减数最后的结果是 1011/2023，最终的结果应该是 1011/2023。
     * 我们把所有的项存起来，完全随机打乱顺序，用来测试 Fraction 类的计算正确性和计算能力。
     */
    public static void testComplex() {
        java.util.ArrayList<Fraction> al = new java.util.ArrayList<Fraction>();
        int quantity = 2022;
        for (int i = 1; i <= quantity; i++) {
            int denominator = i * (i + 1);
            Fraction addend = new Fraction(1, denominator);
            al.add(addend);
        }
        for (int i = 1; i <= quantity - 1; i += 2) {
            int denominator = i * (i + 2);
            Fraction addend = new Fraction(1, denominator);
            al.add(addend.opposite());
        }
        int size = al.size();
        Fraction[] fractions = new Fraction[size];
        al.toArray(fractions);
        for (int i = 0; i < size; i++) {
            int pos = (int) (Math.random() * size);
            Fraction temp = fractions[i];
            fractions[i] = fractions[pos];
            fractions[pos] = temp;
        }
        Fraction sum = Fraction.ZERO;
        for (int i = 0; i < size; i++) {
            sum = sum.add(fractions[i]);
        }
        System.out.println(sum);
    }


}
