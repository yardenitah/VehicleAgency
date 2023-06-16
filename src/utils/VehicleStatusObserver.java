package utils;

import vehicles.VehicleColorAndStatusDecorator;

public interface VehicleStatusObserver {
      void updateStatus(VehicleColorAndStatusDecorator.currentStatus status);
}
