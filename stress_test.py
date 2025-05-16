# Script para pruebas de carga simple a microservicios
from concurrent.futures import ThreadPoolExecutor, as_completed

import requests

# Configura las URLs de los microservicios
services = {
    "delivery": "http://localhost:8082/api/delivery/test",
    "payment": "http://localhost:8083/api/payment/test",
    "restaurant": "http://localhost:8084/api/restaurant/test"
}

# Número de solicitudes por servicio
NUM_REQUESTS = 100000
PAYMENT_URL = "http://localhost:8082/api/payment/test"

def send_request(idx):
    try:
        response = requests.get(PAYMENT_URL, timeout=2)
        if response.status_code == 200:
            print(f"Petición {idx}: Exitosa")
            return True
        else:
            print(f"Petición {idx}: Fallida (status {response.status_code})")
            return False
    except Exception as e:
        print(f"Petición {idx}: Fallida (excepción: {e})")
        return False

success = 0
fail = 0

with ThreadPoolExecutor(max_workers=20) as executor:
    futures = {executor.submit(send_request, i): i for i in range(1, NUM_REQUESTS + 1)}
    for future in as_completed(futures):
        if future.result():
            success += 1
        else:
            fail += 1

print(f"Servicio payment: Exitosas={success}, Fallidas={fail}")