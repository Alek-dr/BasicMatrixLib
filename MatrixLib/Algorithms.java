package MatrixLib;

import java.util.List;

/**
 * Created by alexander on 16.09.17.
 */
public class Algorithms {

    public static Matrix Gauss(Matrix M) throws Exception {
        if (M.isZero()) throw new Exception("Нулевая матрица");
        if ((M.col == 1) & (M.row == 1)) {
            Matrix Res = new Matrix(M.rows(), M.columns());
            Res.matr[0][0] = 1;
            return Res;
        }
        int strI;
        Matrix X = new Matrix(M.row, M.col);
        X.name = M.name;
        X.matr = Matrix.copy(M);
        //Send message here
        List<Integer> delRows = X.excludeZeroRow2();
        if (delRows.size() > 0) {
            X.message = new StringBuilder("Исключим строки:");
            for (int i : delRows) {
                strI = i + 1;
                X.message.append(" " + strI);
            }
            //Send message here
        }
        if (X.col >= X.row) {
            for (int z = 0; z < X.row; z++)
                gaussAlgBody(X, z);
            //Send message here
            return X;
        } else {
            for (int z = 0; z < M.col; z++)
                gaussAlgBody(X, z);
            //Send message here
            return X;
        }
    }

    private static void gaussAlgBody(Matrix x, int z) throws IncompabilityOfColumnsAndRows {
        int strZ;
        double[] RowElem;
        double leadElement;
        int row;
        int strI;
        double divBy;
        strZ = z + 1;
        RowElem = x.findLeadElem(z, z);
        leadElement = RowElem[1];
        row = (int) RowElem[0];
        strI = row+1;
        if (leadElement == 0)
            return;
        if (row != 0) {
            if(z!=row){
                x.swapRows(z, row);
                x.message = new StringBuilder("Поменяли местами строки "+strZ+" и " + strI);
                //Send message here
            }
        }
        if (leadElement != 1) {
            x.divRowByNumber(z, leadElement);
            x.message = new StringBuilder("Поделили строку " + strZ + " на " + x.nf.format(leadElement));
            //Send message here
        }
        for (int i = z + 1; i < x.row; i++) {
            if (x.matr[i][z] == 0)
                continue;
            strI = i + 1;
            divBy = -x.matr[i][z];
            x.addRowMultiplyedByNumber(z, -x.matr[i][z], i);
            x.message = new StringBuilder("Добавили к строке " + strI + " строку " + strZ + ", умноженную на " + x.nf.format(divBy));
        }
    }

}
