/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektpo;
import java.util.*;

/**
 *
 * @author Basia
 */
public class OrganismCompare implements Comparator<Organism> {

    public int compare(Organism m1, Organism m2) {
        if (m1.getInniciative() < m2.getInniciative()) {
            return -1;
        }
        if (m1.getInniciative() > m2.getInniciative()) {
            return 1;
        } else {
            if (m1.GetAge() < m2.GetAge()) {
                return -1;
            }
            if (m1.GetAge() > m2.GetAge()) {
                return 1;
            }
            return 0;
        }
    }
}
