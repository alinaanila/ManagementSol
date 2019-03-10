import java.util.Scanner;

public class Pervaya {
public static void main(String[] args){
String charact = new String();
String yourProblem = new String();
String numOfVarS = "0";
int numOfVar = 0;
int numOfChar = 0;
float [][] Vectorlar = new float [numOfVar][2];
float lyaMax = 0;
Scanner Scan = new Scanner(System.in);
System.out.println("Что выбираем?");
yourProblem = Scan.next();
System.out.println("Количество сравниваемых характеристик: ");
numOfVarS = Scan.next();
numOfChar = ToInt(numOfVarS);
System.out.println("Введите число вариантов");
numOfVarS = Scan.next();
numOfVar = ToInt(numOfVarS);
float [][] MainMatrix = new float[numOfVar][numOfChar];
String[] MatrixOfChar = new String[numOfChar];
/**
 * заполним матрицу характеристик*/
for(int i=0;i<numOfChar;i++) {
	System.out.println("Введите характеристику " );
	MatrixOfChar[i] = Scan.next();
}

/**
 * заполним матрицу вариантов*/
String[] CmprblVar = GetVal(numOfVar);

float[][]Cmpr1Matr = new float[numOfChar][numOfChar];
Cmpr1Matr = Comparess(MatrixOfChar, numOfChar);
/***
 * Вывод матрицы сравнений
 * *
 */
for (int i=0;i<numOfChar;i++){
for (int j=0;j<numOfChar;j++){
        System.out.print(Cmpr1Matr[i][j] + "  " );
        }
System.out.println();
    }
/**
 * Лаба 2 
 */
Vectorlar = Vektorlar(Cmpr1Matr, numOfChar);
/**
 * zаполним матрицу на которую в конце будем умножать, то есть приоритетность характеристик*/
float[] MajorityOfChars = new float[numOfChar];
for (int j=0;j<numOfChar;j++){
    MajorityOfChars[j] = Vectorlar[j][1];
    }
/**proverka
for (int i=0;i<numOfChar;i++){
        System.out.print(String.format(MajorityOfChars[i] + "  " ));
   }
   */
lyaMax = ThirdLR(Cmpr1Matr, numOfChar, Vectorlar);
for(int k = 0;k < numOfChar;k++) {
	int l=k;
	System.out.println("Рассмотрим характеристику " + MatrixOfChar[l]);
	float[][]CmprTemporaryMatr = new float[numOfVar][numOfVar];
	CmprTemporaryMatr = Comparess(CmprblVar, numOfVar);
	/**
	 * Вывод матрицы сравнений
	 */
	for (int i=0;i<numOfVar;i++){
	for (int j=0;j<numOfVar;j++){
	        System.out.print(CmprTemporaryMatr[i][j] + "  " );
	        }
	System.out.println();
	    }
	
	Vectorlar = Vektorlar(CmprTemporaryMatr, numOfVar);
	lyaMax = ThirdLR(CmprTemporaryMatr, numOfVar, Vectorlar);
	for(int m = 0;m < numOfVar;m++) {
		MainMatrix[m][k] = Vectorlar[m][1];
	}	
}
/**
 * проверим результат
 * */
System.out.println("Матрица векторов-приоритетов для всех вариантов и каждой характеристики: ");
for (int i=0;i<numOfVar;i++){
for (int j=0;j<numOfChar;j++){
        System.out.print(MainMatrix[i][j] + "  " );
        }
System.out.println();
    }
/**
 * Вывод матрицы приоритетности характеристик
 */
System.out.println("Приоритетности характеристик: ");
for (int i=0;i<numOfChar;i++){
    System.out.print(String.format(MajorityOfChars[i] + "  " ));
}

float[]FinishMatrix = new float[numOfChar];
for (int i=0;i<numOfVar;i++){
for (int j=0;j<numOfChar;j++){
        FinishMatrix[i] = MainMatrix[i][j] * MajorityOfChars[j] + FinishMatrix[i];
        }
    }
System.out.println(" ");
System.out.println("В результате перемножения предыдущих двух матриц получим следующую: ");
for (int j=0;j<numOfVar;j++){
    System.out.print(FinishMatrix[j] + "  " );
    }
float maxEl = 0;
int maxElInd = 0;
for (int i=0;i<numOfVar;i++){
    if(FinishMatrix[i] > maxEl) {
    	maxEl = FinishMatrix[i];
    	maxElInd = i;
    }
    }
System.out.print("Высший приоритет принадлежит варианту  " + CmprblVar[maxElInd] );

}

public static int ToInt(String aStroke){
int aNumber = 0;
aNumber = Integer.parseInt(aStroke);
return aNumber;
}
public static String[] GetVal(int aNumber){
String[] MatrValue = new String[100];
for(int i=0;i<aNumber;i++){
Scanner Scan = new Scanner(System.in);
System.out.println("Введите вариант");
MatrValue[i] = Scan.next();
}
return MatrValue;
}
public static float[][] Comparess(String[] AMatrix, int aNumber){
float[][] CMPRMatr = new float[aNumber][aNumber];
Scanner Scan = new Scanner(System.in);
for(int i=0;i<aNumber;i++){
for (int j=i+1;j<aNumber;j++){
    System.out.println("Введите предпочтительность " + AMatrix[i] + " относительно " + AMatrix[j]);
    String temporr= Scan.next();
    CMPRMatr[i][j] = Float.parseFloat(temporr);
    CMPRMatr[j][i] = 1 / CMPRMatr[i][j] ;
}
}
for(int i=0;i<aNumber;i++){
for (int j=i;j<aNumber;j++){    
    CMPRMatr[i][i] = 1;    
}
}
return CMPRMatr;
}
public static float[][] Vektorlar(float[][] WorkinM, int aNumber){
	float[][] ResultM = new float[aNumber][2];
	float multi = 1;
	float addic = 0;
	float tempr = 0;
	//sobstvenny vectory
	for(int i = 0;i < aNumber;i++){
		for (int j = 0; j < aNumber; j++){
			multi = WorkinM[i][j]*multi;
		}
		tempr = (float) (Math.round((float) Math.pow(multi, 1.0 / aNumber)*1000.0)/1000.0);
		ResultM[i][0] = tempr;
		multi = 1;
		}	
	for(int i=0;i<aNumber;i++){
		addic = (addic + ResultM[i][0]);
	}		
	// vector prioritet
	for(int i = 0;i < aNumber;i++){
		ResultM[i][1] = (float) (Math.round( ResultM[i][0] / addic * 100.0)/100.0);		
	}
	return ResultM;	
}
public static float ThirdLR(float[][]CmprMatr, int numOfVar, float[][] Vectorlar) {
	float lyamdaMax = 0;
	float indexSogl = 0;
	float otnSogl = 0;
	float [][] Zetlar = new float [numOfVar][2];
	//zapolnim nulevoi stolb obychnymi zet
	for(int i = 0;i < numOfVar;i++) {
		for(int j = 0;j < numOfVar;j++) {
			Zetlar[i][0] = Zetlar[i][0] + CmprMatr[i][j] * Vectorlar[j][1];
		}
	}
	//заполнение второго столба матрицы зетлар элементами зет штрих
	for(int i = 0;i < numOfVar;i++) {
		Zetlar[i][1] = Zetlar[i][0] / Vectorlar[i][1];
	}
	/** proverka
	System.out.println("Матрица значений зет:");
	for (int i=0;i<numOfVar;i++){
		for (int j=0;j<2;j++){
		        System.out.print(String.format(Zetlar[i][j] + "  " ));
		        }
		System.out.println();
		    }
		    */
	// расчет лямды, которая максимальная
	for(int i = 0;i < numOfVar;i++) {
		lyamdaMax = lyamdaMax + Zetlar[i][1];
	}
	lyamdaMax = lyamdaMax / numOfVar;
	// расчет индекса согласованности
	indexSogl = (lyamdaMax - numOfVar) / (numOfVar - 1);	
	// расчет отношения согласованности при помощи средних случайных индексов
	switch(numOfVar) {
	case 3:
		otnSogl = (float) (indexSogl / 580 * 1000.0); 
		break;
	case 4:
		otnSogl = (float) (indexSogl / 900 * 1000.0); 
		break;
	case 5:
		otnSogl = (float) (indexSogl / 1120 * 1000.0); 
		break;
	case 6:
		otnSogl = (float) (indexSogl / 1240 * 1000.0); 
		break;
	case 7:
		otnSogl = (float) (indexSogl / 1320 * 1000.0); 
		break;
	}
	
	/** //proverka
		lyamdaMax = (float) (Math.round( lyamdaMax * 10000.0)/10000.0);
		System.out.println("Лямда = " + lyamdaMax);
	//proverka
		indexSogl = (float) (Math.round( indexSogl * 10000.0)/10000.0);
		System.out.println("Индекс согласованности = " + indexSogl);
		*/
	otnSogl = (float) (Math.round( otnSogl * 1000.0)/1000.0);
	if (otnSogl <= 0.1)
		System.out.println( "Значение отношения согласованности равно " + otnSogl + ". Согласованность матрицы приемлема.");
	else
		System.out.println( "Значение отношения согласованности равно " + otnSogl + ". Согласованность матрицы не приемлема. В вычислениях использовалось значение лямды максимальной,равной " + lyamdaMax);
	return lyamdaMax;	
}
}
