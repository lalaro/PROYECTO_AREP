# Script para pruebas de carga simple a microservicios
from concurrent.futures import ThreadPoolExecutor, as_completed

import requests

# Configura las URLs de tus microservicios
services = {
    "delivery": "http://localhost:8082/api/delivery/test",
    "payment": "http://localhost:8083/api/payment/test",
    "restaurant": "http://localhost:8084/api/restaurant/test"
}

# Número de solicitudes por servicio
NUM_REQUESTS = 100000
PAYMENT_URL = "http://localhost:8082/api/payment/test"

def send_request():
    try:
        response = requests.get(PAYMENT_URL, timeout=2)
        return response.status_code == 200
    except Exception:
        return False

success = 0
fail = 0

with ThreadPoolExecutor(max_workers=20) as executor:
    futures = [executor.submit(send_request) for _ in range(NUM_REQUESTS)]
    for future in as_completed(futures):
        if future.result():
            success += 1
        else:
            fail += 1

print(f"Servicio payment: Exitosas={success}, Fallidas={fail}")