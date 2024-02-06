// La palabra async se utiliza antes del método para indicar que devolvera un promesa.
async function fetchData() {
    try {
        // Utilizamos la palabra await dentro de la function async para esperar la resolución de la promesa
        // Esto pausa la ejecución de de la función async hasta que la promesa se resolve o reject y nos devuelva la respuesta de la promesa.
      const response = await fetch('https://pokeapi.co/api/v2/pokemon/ditto');
      
      if (!response.ok) {
        throw new Error('Error al obtener los datos');
      }
      
      const data = await response.json();
      
      // Procesar los datos obtenidos
      console.log('Datos obtenidos:', data);
      console.log('--------------------------------------------');

      return data;
    } catch (error) {
      console.error('Error:', error.message);
      throw error;
    }
  }

  async function processData(){
    try {
        const data = await fetchData();
        const processData = data.abilities.map(item => item.ability.name.toUpperCase());

        return processData;
    } catch (error) {
        
    }
  }

  (async () => {
    try {
      const processedData = await processData();
      // Realizar más operaciones con los datos procesados
      console.log('MAPEO DE HABILIDADES')
      console.log('--------------------------------------------');
      console.log(processedData)
    } catch (error) {
      console.error('Error general:', error.message);
    }
  })();