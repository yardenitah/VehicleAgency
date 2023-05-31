package graphic;
import utils.Tuple;
import javax.swing.*;
public interface UpdatingVehiclesList {
    public void updateVehicleList();
    public void updatingDataPleaseWait(Thread thread);
    public Tuple<ButtonGroup, Box> printAllVehiclesPictures();
    public Box printAllLandVehiclesPictures(ButtonGroup buttonGroup);
    public  Box printAllAirVehiclesPictures(ButtonGroup buttonGroup);
    public Box printAllWaterVehiclesPictures(ButtonGroup buttonGroup);
}
