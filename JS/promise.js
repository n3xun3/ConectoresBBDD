const asyncFunction1 = () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve('Resultado de la función 1');
    }, 500);
  });
};

const asyncFunction2 = () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve('Resultado de la función 2');
    }, 2500);
  });
};

const asyncFunction3 = () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve('Resultado de la función 3');
    }, 1500);
  });
};

// Uso de Promise.any() se resuelve tan pronto como una de las promesas se cumple
/*Promise.any([asyncFunction1(), asyncFunction2(), asyncFunction3()])
  .then((result) => {
    console.log('Primera promesa cumplida:', result);
  })
  .catch((error) => {
    console.log('Todas las promesas fueron rechazadas:', error);
  });*/

// Promise.allSettled() espera a que todas las promesas se resuelvan o se rechacen y proporciona información sobre el estado de cada una de ellas. 
  Promise.allSettled([asyncFunction1(), asyncFunction2(), asyncFunction3()])
  .then((results) => {
    results.forEach((result) => {
      if (result.status === 'fulfilled') {
        console.log('Promesa cumplida:', result.value);
      } else {
        console.log('Promesa rechazada:', result.reason);
      }
    });
  });