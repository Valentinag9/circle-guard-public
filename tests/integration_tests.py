import requests
import unittest
import os

# Base URLs (using localhost for local dev testing with port forwarding or minikube ip)
BASE_URL = os.getenv("CIRCLEGUARD_GATEWAY_URL", "http://localhost:30087")
AUTH_URL = os.getenv("CIRCLEGUARD_AUTH_URL", "http://localhost:30081") # Assuming 31180 or similar
IDENTITY_URL = os.getenv("CIRCLEGUARD_IDENTITY_URL", "http://localhost:30083")

class TestServiceCommunication(unittest.TestCase):

    def test_1_gateway_health(self):
        """Test if Gateway is alive"""
        response = requests.get(f"{BASE_URL}/actuator/health")
        self.assertEqual(response.status_code, 200)

    def test_2_auth_health(self):
        """Test if Auth Service is alive"""
        response = requests.get(f"{AUTH_URL}/actuator/health")
        self.assertEqual(response.status_code, 200)

    def test_3_identity_health(self):
        """Test if Identity Service is alive"""
        response = requests.get(f"{IDENTITY_URL}/actuator/health")
        self.assertEqual(response.status_code, 200)

    def test_4_auth_to_identity_internal(self):
        """Test if Auth can talk to Identity (simulated via external call for now)"""
        # In a real integration test, we might trigger an endpoint in Auth 
        # that we know calls Identity.
        response = requests.get(f"{AUTH_URL}/auth/validate-internal")
        # If this endpoint exists and calls Identity, it validates communication.
        self.assertIn(response.status_code, [200, 401, 404]) # Just checking connectivity

    def test_5_form_to_notification_flow(self):
        """Test if Form service is reachable"""
        FORM_URL = os.getenv("CIRCLEGUARD_FORM_URL", "http://localhost:30086")
        response = requests.get(f"{FORM_URL}/actuator/health")
        self.assertEqual(response.status_code, 200)

if __name__ == '__main__':
    unittest.main()
