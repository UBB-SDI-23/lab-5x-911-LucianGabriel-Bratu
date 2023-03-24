package com.example.Lab2;

import com.example.Lab2.Model.Car;
import com.example.Lab2.Model.DTOs.DealershipDTO;
import com.example.Lab2.Model.DTOs.DealershipStatisticDTO;
import com.example.Lab2.Model.DTOs.NrCarsStatisticDTO;
import com.example.Lab2.Model.Dealership;
import com.example.Lab2.Model.Mechanic;
import com.example.Lab2.Model.PerformsMaintenance;
import com.example.Lab2.Repository.CarRepository;
import com.example.Lab2.Repository.DealershipRepository;
import com.example.Lab2.Repository.MaintenanceRepository;
import com.example.Lab2.Repository.MechanicRepository;
import com.example.Lab2.Service.DealershipService;
import com.example.Lab2.Service.MaintenanceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class Lab4ApplicationTests {
	@MockBean
	private DealershipRepository dealershipRepository;

	@Autowired private DealershipService dealershipService;

	@MockBean
	private CarRepository carRepository;

	@MockBean
	private MechanicRepository mechanicRepository;

	@MockBean
	private MaintenanceRepository maintenanceRepository;

	@Autowired private MaintenanceService maintenanceService;

	@Test
	void getDealershipStatisticTest() {
		Dealership dealership1 = Dealership.builder()
				.dealershipID(1L)
				.name("Cluj Dealership")
				.address("Cluj")
				.capacity(20)
				.reputation(2.0)
				.hasService(true)
				.offersTradeIn(true)
				.build();

		DealershipDTO dealershipDTO1 = new DealershipDTO(dealership1.getDealershipID(), dealership1.getName(),
				dealership1.getCapacity(), dealership1.getAddress(), dealership1.getReputation(), dealership1.isHasService(),
				dealership1.isOffersTradeIn());

		Dealership dealership2 = Dealership.builder()
				.dealershipID(2L)
				.name("Bucharest Dealership")
				.address("Bucharest")
				.capacity(30)
				.reputation(1.0)
				.hasService(true)
				.offersTradeIn(true)
				.build();

		DealershipDTO dealershipDTO2 = new DealershipDTO(dealership2.getDealershipID(), dealership2.getName(),
				dealership2.getCapacity(), dealership2.getAddress(), dealership2.getReputation(), dealership2.isHasService(),
				dealership2.isOffersTradeIn());

		Car car1 = Car.builder()
				.carID(1L)
				.carManufacturer("Mazda")
				.carModel("3")
				.carRetailPrice(12000)
				.carTopSpeed(170.0)
				.carWeight(1500.0)
				.build();
		car1.setDealership(dealership1);
		carRepository.save(car1);
		dealershipRepository.save(dealership1);

		Car car2 = Car.builder()
				.carID(1L)
				.carManufacturer("Mazda")
				.carModel("3")
				.carRetailPrice(10000)
				.carTopSpeed(170.0)
				.carWeight(1500.0)
				.build();

		car2.setDealership(dealership2);
		carRepository.save(car2);
		dealershipRepository.save(dealership2);

		when(dealershipService.fetchStatisticForDealershipsInventories()).thenReturn(
				Stream.of(new DealershipStatisticDTO(
						12000,
						dealershipDTO1
						),
						new DealershipStatisticDTO(
						10000,
								dealershipDTO2
						)).collect(Collectors.toList()));
		System.out.println("Test 1 passed");
	}

	@Test
	void getNrMechanicsStatisticTest(){
		Car car1 = Car.builder()
				.carID(1L)
				.carManufacturer("Mazda")
				.carModel("3")
				.carRetailPrice(12000)
				.carTopSpeed(170.0)
				.carWeight(1500.0)
				.build();

		Car car2 = Car.builder()
				.carID(1L)
				.carManufacturer("Mazda")
				.carModel("3")
				.carRetailPrice(10000)
				.carTopSpeed(170.0)
				.carWeight(1500.0)
				.build();

		Mechanic mechanic1 = Mechanic.builder()
				.mechanicID(1L)
				.cnp("55723758932")
				.address("Cluj")
				.experience(4)
				.favouriteCarBrand("Lexus")
				.name("Marcel")
				.build();

		Mechanic mechanic2 = Mechanic.builder()
				.mechanicID(2L)
				.cnp("55723758932")
				.address("Cluj")
				.experience(2)
				.favouriteCarBrand("Lexus")
				.name("Larry")
				.build();

		PerformsMaintenance performsMaintenance1 = PerformsMaintenance.builder()
				.maintenanceID(1L)
				.urgency("not urgent")
				.difficulty("easy")
				.build();
		performsMaintenance1.setMechanic(mechanic1);
		performsMaintenance1.setCar(car1);

		PerformsMaintenance performsMaintenance2 = PerformsMaintenance.builder()
				.maintenanceID(2L)
				.urgency("not urgent")
				.difficulty("easy")
				.build();

		performsMaintenance2.setMechanic(mechanic2);
		performsMaintenance2.setCar(car1);

		List<PerformsMaintenance> performsMaintenanceList = new ArrayList<>();

		performsMaintenanceList.add(performsMaintenance1);
		performsMaintenanceList.add(performsMaintenance2);

		car1.setPerformsMaintenancesList(performsMaintenanceList);

		performsMaintenanceList.clear();

		PerformsMaintenance performsMaintenance3 = PerformsMaintenance.builder()
				.maintenanceID(3L)
				.urgency("not urgent")
				.difficulty("easy")
				.build();

		performsMaintenance3.setMechanic(mechanic1);
		performsMaintenance3.setCar(car2);

		performsMaintenanceList.add(performsMaintenance3);

		car2.setPerformsMaintenancesList(performsMaintenanceList);

		performsMaintenanceList.clear();

		performsMaintenanceList.add(performsMaintenance1);
		performsMaintenanceList.add(performsMaintenance3);

		mechanic1.setPerformsMaintenancesList(performsMaintenanceList);

		performsMaintenanceList.clear();

		performsMaintenanceList.add(performsMaintenance2);

		mechanic2.setPerformsMaintenancesList(performsMaintenanceList);

		carRepository.save(car1);
		carRepository.save(car2);

		mechanicRepository.save(mechanic1);
		mechanicRepository.save(mechanic2);

		maintenanceRepository.save(performsMaintenance1);
		maintenanceRepository.save(performsMaintenance2);
		maintenanceRepository.save(performsMaintenance3);

		when(maintenanceService.fetchCarsMaintenanceStatistic()).thenReturn(
				Stream.of(new NrCarsStatisticDTO(1L, "Mazda", "3", 12000, 170, 1500, 2),
						new NrCarsStatisticDTO(2L, "Mazda", "3", 10000, 170, 1500, 1)).collect(Collectors.toList())
		);
		System.out.println("Test 2 passed");
	}
}
