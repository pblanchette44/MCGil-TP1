package creditStuff;

public class YearlyBudget {

	public static void main(String[] args) {
		
		double preTaxIncome = Double.parseDouble(args[0]);
		double balance =  Double.parseDouble(args[1]);
		double interestRate =  Double.parseDouble(args[2]);
		long creditCard = Long.parseLong(args[3]);
		double rent =  Double.parseDouble(args[4]);
		
		if(!validateCreditCard(creditCard))
		{
		System.out.println("Invalid card");
		System.exit(0);
		}
		
		double[] temp =buildExpenses(rent);
		
		printBalance(balance,interestRate,temp,buildPayments(preTaxIncome));
		
		double bob = preTaxIncome/12;
		for(int i =0; i < 12;i++)
		{
			System.out.println(monthlySavings(bob,temp[i]));
		}
		
	}

	static public double calculateTaxes(double yearlyIncome, double bracket1Dollars, double bracket1Rate,
			double bracket2Dollars, double bracket2Rate, double bracket3Dollars, double bracket3Rate) {
		double temp = yearlyIncome;
		
		double tax1 = 0;
		double tax2 = 0;
		double tax3 = 0;
		
		temp -= bracket1Dollars;
		
		if(temp <= bracket2Dollars)
		{
		tax1 = temp;
		} else {
			tax1 = bracket2Dollars-bracket1Dollars;
			temp -= bracket2Dollars-tax1;
		}
		
		if(temp <= bracket3Dollars)
		{
		tax2 = temp;
		} else {
			tax2 = bracket3Dollars-bracket2Dollars;
			temp -= bracket3Dollars-tax2;
		}
		
		tax3 = temp;
		
		tax1 = tax1*(bracket1Rate/100);
		tax2 = tax2*(bracket2Rate/100);
		tax3 = tax3*(bracket3Rate/100);
		
		return tax1+tax2+tax3;
	}
	
	static public double monthlySavings(double income, double expenses)
	{
		return income-expenses;
	}
	

	static public boolean validateCreditCard(long input) {
		long temp = input;

		int sumE = 0;
		int sumO = 0;

		for (int i = 0; i < 16; i++) {
			long bob = temp % 10;
			temp = temp / 10;

			if (i % 2 == 1) {
				sumO += (bob * 2) % 9;
			} else {
				sumE += bob;
			}
		}
		if (sumE + sumO % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	static public double[] buildExpenses(double rent) {

		double[] expenses = new double[12];

		for (int i = 0; i < expenses.length; i++) {
			if (i == 0 || i == 5) {
				expenses[i] = rent + 600 + 200;
			} else if (i == 8) {
				expenses[i] = rent + 600 + 300 + 100;
			} else if (i == 3 || i == 6) {
				expenses[i] = rent + 600 + 100;
			} else if (i == 11) {
				expenses[i] = rent + 600 + 200;
			}
		}

		return expenses;
	}

	static public double[] buildPayments(double income) {

		double[] payments = new double[12];

		for (int i = 0; i < 12; i++) {
			double value = 0;
			if (i == 11) {
				value = ((income / 12) * 0.10);
				value += 150;
			} else if (i == 8) {
				value = ((income / 12) * 0.10);
				value += 200;
			} else {
				value = ((income / 12) * 0.10);
			}
			payments[i] = value;
		}

		return payments;
	}
	
	static public void printBalance(double creditBalance,double yearlyInterest,double[] monthlyExpenses,double[] arrayPayments)
	{
		for(int i =0; i < 12;i++)
		{
			creditBalance += monthlyExpenses[i];
			creditBalance -= arrayPayments[i];		
			creditBalance += creditBalance*((yearlyInterest/12)/100);
			System.out.println("Month" + i + "balance " + creditBalance);
		}
	}
}
