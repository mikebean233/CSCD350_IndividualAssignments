package fraction;

import java.util.ArrayList;
import java.util.Arrays;



public class Fraction implements Comparable<Fraction>
{
	private int _num;
	private int _den;
	private boolean _isPositive;
	
	public Fraction(int num, int den)
	{
		if(den == 0)
			throw new IllegalArgumentException("Attempt to devide by zero");
		
		if(num == 0)
		{
			_num = 0;
			_den = 1;
		}
		else
		{
		    _num = num;
		    _den = den;
		}
		
		_isPositive = ((_num < 0 && _den < 0) || (_num > 0 && _den > 0)) ? true : false;
		
		if(_num < 0 && _den < 0 || (!_isPositive && _den < 0) )
		{
			_den = _den * -1;
			_num = _num * -1;
		}
		
		reduce();
	}
	
	public int getNum(){return _num;}
	public int getDen(){return _den;
}

	@Override
	public int compareTo(Fraction o) throws NullPointerException
	{
		if(o == null)
			throw new NullPointerException("Fraction.compareTo received a null Fraction reference");
		
		if(_num == 0 && o.getNum() == 0)
			return 0;
		
		
		return _num - o.getNum();
	}
	
	public double realValue() throws ArithmeticException
	{
		return ((double) _num / (double) _den);
	};
	
	public Fraction add(Fraction otherFraction) throws NullPointerException
	{
		if(otherFraction == null)
			throw new NullPointerException("Fraction.add received a null Fraction reference");
		
		int newDen = _den * otherFraction.getDen();
		return new Fraction(_num * otherFraction.getDen() + (otherFraction.getNum() * _den), newDen);
	}

	public Fraction sub(Fraction otherFraction)
	{
	    if(otherFraction == null)
		    throw new NullPointerException("Fraction.sub received a null Fraction reference");
	    
	    int newDen = _den * otherFraction.getDen();
	    return new Fraction(_num * otherFraction.getDen() - (otherFraction.getNum() * _den), newDen);
	}

	public Fraction multiply(Fraction otherFraction)
	{
	    if(otherFraction == null)
		    throw new NullPointerException("Fraction.multiply received a null Fraction reference");
	    
	    return new Fraction(_num * otherFraction.getNum(), _den * otherFraction.getDen());
	}
	
	private void reduce()
	{
		int gfc = getGCF(_num, _den);
		
		_num = _num / gfc;
		_den = _den / gfc;
	}

	private int getGCF(int a, int b)
	{
		ArrayList<Integer> numFactors = getFactors(_num);
		ArrayList<Integer> denFactors = getFactors(_den);
		
		Integer greatestFactor = new Integer(1);
		
		for(Integer thisFactor: numFactors)
			if(denFactors.contains(thisFactor) && thisFactor > greatestFactor)
				greatestFactor = thisFactor;
		return greatestFactor.intValue();
	}

	private ArrayList<Integer> getFactors(int number)
	{
		number = (number < 0) ? -1 * number : number;
		
		ArrayList<Integer> knownFactors = new ArrayList<>();
		
		knownFactors.add(1);
		knownFactors.add(number);
		
		for(int i = number; i >1; --i)
		{
			if(!knownFactors.contains(new Integer(i)) && number % i == 0)
				knownFactors.add(i);
		}
		return knownFactors;
	}
	
	@Override
	public String toString()
	{
		return (new Integer(_num)).toString() + "/" + (new Integer(_den)).toString();
	}
	
	@Override
	public boolean equals(Object that)
	{
		if(that == null)
			throw new NullPointerException("Fraction.equals received a null reference");
		if(!(that instanceof Fraction))
			return false;
		
		return realValue() == ((Fraction)that).realValue() ;
	}
}
