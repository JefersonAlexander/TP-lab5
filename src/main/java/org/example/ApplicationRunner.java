package org.example;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import org.example.exception.GradeNotFoundException;
import org.example.model.Grade;
import org.example.repository.GradeUsingFileRepositoryImpl;
import org.example.service.AcademicRecordService;
import org.example.service.AcademicRecordServiceImpl;

public class ApplicationRunner {
  public static void main(String[] args) {

    AcademicRecordService academicRecordService =
        new AcademicRecordServiceImpl(new GradeUsingFileRepositoryImpl());


    System.out.println("Notas iniciales");

    mostrarNotas(academicRecordService);

    academicRecordService.addGrade(new Grade("PARCIAL", 4.5D, LocalDate.now()));

    System.out.println("Notas despues de adicionar una nueva");

    mostrarNotas(academicRecordService);

    System.out.println(
        MessageFormat.format(
            "Suma de número calificaciones: {0}", academicRecordService.sumNumberOfGrades()));

    System.out.println(
        MessageFormat.format("Promedio: {0}", academicRecordService.calculateAverage()));

    System.out.println(
        "Consulta una nota de un proyecto que no existe");

    consultarNota(academicRecordService, "Unidad 10");
  }

  private static void consultarNota(
      AcademicRecordService academicRecordService, String nombreProyecto) {
    try {
      System.out.println(academicRecordService.getGrade(nombreProyecto));
    } catch (GradeNotFoundException e) {
      System.out.println(
          MessageFormat.format("No se encontró una nota para la unidad {0} ", nombreProyecto));
    }
  }

  private static void mostrarNotas(AcademicRecordService academicRecordService) {
    List<Grade> gradeList = academicRecordService.listAllGrades();
    gradeList.forEach(System.out::println); // Impresion en estilo de programacion funcional
  }
}
