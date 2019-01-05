package chapter10;

/**
 * @author huangyichun
 * @date 2019/1/5
 */
public class NullPointerTest {

    public String getCarInsuranceName(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
        //return person.getCar().getInsurance().getName();
    }


}
