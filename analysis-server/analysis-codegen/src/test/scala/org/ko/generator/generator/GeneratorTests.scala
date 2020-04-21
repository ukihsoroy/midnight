package org.ko.generator.generator

import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.ko.generator.conf.GeneratorConf
import org.ko.generator.core._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@Test
@SpringBootTest
@RunWith(classOf[SpringRunner])
class GeneratorTests {

  val tables = List(
    "t_send_code_log"
  )

  @Test
  def generatorEntity (): Unit = {
    entity.executor(tables)
  }

  @Test
  def generatorRepository (): Unit = {
    repository.executor(tables)
  }

  @Test
  def generatorCondition (): Unit = {
    condition.executor(tables)
  }

  @Test
  def generatorDTO (): Unit = {
    dto.executor(tables)
  }

  @Test
  def generatorService (): Unit = {
    service.executor(tables)
  }

  @Test
  def generatorController (): Unit = {
    controller.executor(tables)
  }

  @Autowired
  private val entity: EntityGenerator = null

  @Autowired
  private val repository: RepositoryGenerator = null

  @Autowired
  private val condition: ConditionGenerator = null

  @Autowired
  private val dto: DTOGenerator = null

  @Autowired
  private val service: ServiceGenerator = null

  @Autowired
  private val controller: ControllerGenerator = null

  @Before
  def before (): Unit = {
    val dataSource = GeneratorConf.dataSource()
    entity.dataSource(dataSource)
    repository.dataSource(dataSource)
    condition.dataSource (dataSource)
    dto.dataSource(dataSource)
    service.dataSource(dataSource)
    controller.dataSource(dataSource)
  }
}
